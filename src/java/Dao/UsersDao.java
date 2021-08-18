
package Dao;

import Connection.ConnectionCassandra;
import Model.Users;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {

    private final String host = "localhost";
    private final int port = 9042;

    public Users GetUserByCredentialsLogin(String usuario, String clave) {
        Users user = new Users();
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String getData = "SELECT * FROM corviche.users WHERE user = ? AND password = ? ALLOW FILTERING;";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(getData);
            BoundStatement boundStatement = preparedStatement.bind(usuario, clave);
            ResultSet resultSet = connectionCassandra.GetSession().execute(boundStatement);
            resultSet.forEach(rs -> {
                user.setId(rs.getInt("id"));
                user.setUser(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setImg(rs.getString("img"));
            });
            connectionCassandra.CloseConnect();
            return user;
        } catch (Exception e) {
            System.out.println("Error en Dao.UsersDao.GetUserByCredentialsLogin: " + e.getMessage());
            user.setId(0);
            user.setUser("null");
            user.setPassword("null");
            user.setUsername("null");
            user.setImg("null");
            return user;
        }
    }

    public List<Users> GetAllUsers() {
        List<Users> users = new ArrayList<>();
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String getData = "SELECT * FROM corviche.users;";
            ResultSet resultSet = connectionCassandra.GetSession()
                    .execute(getData);
            resultSet.forEach(rs -> {
                Users u = new Users(rs.getInt("id"), rs.getString("user"), 
                        rs.getString("password"), rs.getString("username"), 
                        rs.getString("img"));
                users.add(u);
            });
            connectionCassandra.CloseConnect();
            return users;
        } catch (Exception e) {
            System.out.println("Error en Dao.UsersDao.GetAllUsers: " + 
                    e.getMessage());
            return users;
        }
    }
    
    public Users InsertUser (Users user) {
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String insertData = "INSERT INTO corviche.users (id, user, password, username, img) VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(insertData);
            BoundStatement boundStatement = preparedStatement.bind(user.getId(), user.getUser(), user.getPassword(), user.getUsername(), user.getImg());
            connectionCassandra.GetSession().execute(boundStatement);
            connectionCassandra.CloseConnect();
            return user;
        } catch (Exception e) {
            System.out.println("Error en Dao.UsersDao.InsertUser: " + e.getMessage());
            return null;
        }
    }
    
    public Users UpdateUser (Users user) {
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String updateData = "UPDATE corviche.users SET user = ?, password = ?, username = ?, img = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(updateData);
            BoundStatement boundStatement = preparedStatement.bind(user.getUser(), user.getPassword(), user.getUsername(), user.getImg(), user.getId());
            connectionCassandra.GetSession().execute(boundStatement);
            connectionCassandra.CloseConnect();
            return user;
        } catch (Exception e) {
            System.out.println("Error en Dao.UsersDao.UpdateUser: " + e.getMessage());
            return null;
        }
    }
    
    public int DeleteUser (int id) {
        try {
            ConnectionCassandra connectionCassandra = new ConnectionCassandra();
            connectionCassandra.ConnectCassandra(host, port);
            final String deleteData = "DELETE FROM corviche.users WHERE id = ?;";
            PreparedStatement preparedStatement = connectionCassandra.GetSession().prepare(deleteData);
            BoundStatement boundStatement = preparedStatement.bind(id);
            connectionCassandra.GetSession().execute(boundStatement);
            connectionCassandra.CloseConnect();
            return id;
        } catch (Exception e) {
            System.out.println("Error en Dao.UsersDao.DeleteUser: " + e.getMessage());
            return 0;
        }
    }
}
