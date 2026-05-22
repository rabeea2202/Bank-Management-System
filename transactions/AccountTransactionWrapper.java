package transactions;
import models.*;
import utils.*;
import java.io.Serializable;
import java.util.ArrayList;
public class AccountTransactionWrapper implements Serializable {
    private Account account;
    private ArrayList<Transaction> transactions;
    public AccountTransactionWrapper() {}
    public AccountTransactionWrapper(Account account) { this.account = account; this.transactions = new ArrayList<>(); }
    public AccountTransactionWrapper(Account account, ArrayList<Transaction> transactions) { this.account = account; this.transactions = transactions; }
    public Account getAccount() { return account; }
    public ArrayList<Transaction> getTransactions() { return transactions; }
}
