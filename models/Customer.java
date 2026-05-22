package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class Customer extends Person implements Serializable {
    private Address address;
    public Customer() {}
    public Customer(String name, long phone) { super(name, phone); }
    public Customer(String name, long phone, Address add) { super(name, phone); this.address = add; }
}
