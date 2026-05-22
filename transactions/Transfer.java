package transactions;
import models.*;
import utils.*;
import java.time.LocalDate;
import java.io.Serializable;
public class Transfer extends Transaction implements Serializable {
    public Transfer(String type, long transId, long fromAcc, Account toAcc, LocalDate date, double amount) { super(type, transId, amount, date); }
}
