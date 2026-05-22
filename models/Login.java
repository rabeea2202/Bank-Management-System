package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class Login implements Serializable {
    private String username;
    private String password;
    public Login() {}
    public Login(String username, String password) { this.username = username; this.password = password; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
