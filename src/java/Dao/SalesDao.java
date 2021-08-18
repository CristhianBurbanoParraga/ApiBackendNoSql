
package Dao;

import Connection.ConnectionCassandra;
import Model.Sales;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesDao {
    
    private final String host = "localhost";
    private final int port = 9042;
    
    public List<Sales> GetSalesByUser (String userName) {
        List<Sales> sales = new ArrayList<>();
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String getData = "SELECT * FROM corviche.sales WHERE username = ? ALLOW FILTERING;";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(getData);
            BoundStatement boundStatement = preparedStatement.bind(userName);
            ResultSet resultSet = connectionCassandra.GetSession().execute(boundStatement);
            resultSet.forEach(rs -> {
                Sales sale = new Sales(rs.getInt("id"), rs.getInt("quantity"), FloatDecimalFormat(rs.getFloat("unitprice")), FloatDecimalFormat(rs.getFloat("total")), rs.getString("observation"), rs.getString("username"));
                sales.add(sale);
            });
            connectionCassandra.CloseConnect();
            return sales;
        } catch (Exception e) {
            System.out.println("Error en Dao.SalesDao.GetSalesByUser: " + e.getMessage());
            return sales;
        }
    }
    
    public List<Sales> GetAllSales () {
        List<Sales> sales = new ArrayList<>();
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String getData = "SELECT * FROM corviche.sales;";
            ResultSet resultSet = connectionCassandra.GetSession().execute(getData);
            resultSet.forEach(rs -> {
                Sales sale = new Sales(rs.getInt("id"), rs.getInt("quantity"), FloatDecimalFormat(rs.getFloat("unitprice")), FloatDecimalFormat(rs.getFloat("total")), rs.getString("observation"), rs.getString("username"));
                sales.add(sale);
            });
            connectionCassandra.CloseConnect();
            return sales;
        } catch (Exception e) {
            System.out.println("Error en Dao.SalesDao.GetAllSales: " + e.getMessage());
            return sales;
        }
    }
    
    public Sales InsertSale (Sales sale) {
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String insertData = "INSERT INTO corviche.sales (id, quantity, unitprice, total, observation, username) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(insertData);
            BoundStatement boundStatement = preparedStatement.bind(sale.getId(), sale.getQuantity(), (float) sale.getUnitprice(),(float) sale.getTotal(), sale.getObservation(), sale.getUsername());
            connectionCassandra.GetSession().execute(boundStatement);
            connectionCassandra.CloseConnect();
            return sale;
        } catch (Exception e) {
            System.out.println("Error en Dao.UsersDao.InsertUser: " + e.getMessage());
            return null;
        }
    }
    
    public Sales UpdateSale (Sales sale) {
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String updateData = "UPDATE corviche.sales SET quantity = ?, unitprice = ?, total = ?, observation = ?, username = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(updateData);
            BoundStatement boundStatement = preparedStatement.bind(sale.getQuantity(), (float) sale.getUnitprice(), (float) sale.getTotal(), sale.getObservation(), sale.getUsername(), sale.getId());
            connectionCassandra.GetSession().execute(boundStatement);
            connectionCassandra.CloseConnect();
            return sale;
        } catch (Exception e) {
            System.out.println("Error en Dao.SalesDao.UpdateSale: " + e.getMessage());
            return null;
        }
    }
    
    public int DeleteSale (int id) {
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String deleteData = "DELETE FROM corviche.sales WHERE id = ?;";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(deleteData);
            BoundStatement boundStatement = preparedStatement.bind(id);
            connectionCassandra.GetSession().execute(boundStatement);
            connectionCassandra.CloseConnect();
            return id;
        } catch (Exception e) {
            System.out.println("Error en Dao.SalesDao.DeleteSale: " + e.getMessage());
            return 0;
        }
    }
    
    private float FloatDecimalFormat (float num) {
        float n = (float) num;
        float number = BigDecimal.valueOf(n)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN)
                .floatValue();
        return number;
    }
}
