
package Model;

/**
 *
 * @author Cristhian
 */
public class Sales {
    
    private int id;
    private int quantity;
    private float unitprice;
    private float total;
    private String observation;
    private String username;

    public Sales() {
    }

    public Sales(int id, int quantity, float unitprice, float total, String observation, String username) {
        this.id = id;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.total = total;
        this.observation = observation;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
