package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class Person implements Serializable {
    private String name;
    private long phone;
    public Person() {}
    public Person(String name, long phone) { this.name = name; this.phone = phone; }
    public String getName() { return name; }
    public long getPhone() { return phone; }
    public void setName(String name) { this.name = name; }
    public void setPhone(long phone) { this.phone = phone; }
}
