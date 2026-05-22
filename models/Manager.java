package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class Manager extends Person implements Serializable {
    private Address address;
    private Login login;
    private long id;
    public Manager() {}
    public Manager(String name, long phone, Address address, Login login, long id) { super(name, phone); this.address = address; this.login = login; this.id = id; }
    public Manager(String name, long phone, Address address, Login login) { super(name, phone); this.address = address; this.login = login; }
    public Login getLogin() { return login; }
}
