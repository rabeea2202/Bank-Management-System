package transactions;
import models.*;
import utils.*;
import java.io.Serializable;
import java.time.LocalDate;
public abstract class Transaction implements Serializable {
    private String type;
    private long transId;
    private double amount;
    private LocalDate date;
    public Transaction() {}
    public Transaction(String type, long transId, double amount, LocalDate date) { this.type = type; this.amount = amount; this.date = date; }
    public String transaction_history() { return type + " amount: " + amount + " on " + date; }
}
