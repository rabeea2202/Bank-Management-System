package models;
import transactions.*;
import utils.*;
import java.io.Serializable;
public class checkingAccount extends Account implements Serializable {
    public checkingAccount() {}
    public checkingAccount(String n, long phone, Address add, long acc, Login log) { super(n, phone, add, acc, log); }
}
