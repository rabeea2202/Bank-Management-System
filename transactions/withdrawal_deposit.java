package transactions;
import models.*;
import utils.*;
import java.time.LocalDate;
import java.io.Serializable;
public class withdrawal_deposit extends Transaction implements Serializable {
    public withdrawal_deposit(String type, long transId, long accNo, LocalDate date, double amount) { super(type, transId, amount, date); }
}
