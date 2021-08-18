
package Model;

public class Users {
    
    private int id;
    private String user;
    private String password;
    private String username;
    private String img;

    public Users() {
    }

    public Users(int id, String user, String password, String username, String img) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.username = username;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
