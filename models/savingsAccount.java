package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class savingsAccount extends Account implements Serializable {
    public savingsAccount() {}
    public savingsAccount(String n, long phone, Address add, long acc, Login log) { super(n, phone, add, acc, log); }
}
