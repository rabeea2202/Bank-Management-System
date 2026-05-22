package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class Address implements Serializable {
    private String city;
    private int houseNo;
    private int streetNo;
    public Address() {}
    public Address(String city, int houseNo, int streetNo) { this.city = city; this.houseNo = houseNo; this.streetNo = streetNo; }
}
