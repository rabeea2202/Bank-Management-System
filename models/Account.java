package models;
import transactions.*;
import utils.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Account extends Customer implements Serializable {
    private long AccountNo;
    private double balance;
    private ArrayList<Transaction> transactions;
    private Login login;

    public Account() {
    }

    public Account(String n, long phone, Address add, long acc, Login log) {

        super(n, phone, add);

        AccountNo = acc;

        login = log;

        balance = 0.0;
        transactions = new ArrayList<>();

    }

    public long getAccountNo() {
        return AccountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransaction() {
        return transactions;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            withdrawal_deposit trans = new withdrawal_deposit("deposit", generate_Random.generateRandomTransactionID(),
                    getAccountNo(), LocalDate.now(), amount);
            ObjectInputStream objectInputStream = null;
            Path filePath = Paths.get("Account");
            ArrayList<AccountTransactionWrapper> accounts = new ArrayList<>();

            // Check if the file already exists
            if (Files.exists(filePath)) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(filePath.toString()));
                    accounts = (ArrayList<AccountTransactionWrapper>) objectInputStream.readObject();
                } catch (EOFException ex) {
                    System.out.println("End of file reached.");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            // Find the account in the ArrayList and append the transaction
            boolean accountFound = false;
            for (AccountTransactionWrapper accountWrapper : accounts) {
                if (accountWrapper.getAccount().getAccountNo() == getAccountNo()) {
                    accountWrapper.getTransactions().add(trans);
                    accountWrapper.getAccount().setBalance(balance);
                    accountFound = true;
                    break;
                }
            }

            // If the account was not found, create a new AccountTransactionWrapper
            if (!accountFound) {
                AccountTransactionWrapper newAccountWrapper = new AccountTransactionWrapper(this);
                newAccountWrapper.getTransactions().add(trans);
                newAccountWrapper.getAccount().setBalance(balance);
                accounts.add(newAccountWrapper);
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                objectOutputStream.writeObject(accounts);
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean withdraw(double amount) {

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(balance);
            withdrawal_deposit trans = new withdrawal_deposit("withdraw", generate_Random.generateRandomTransactionID(),
                    getAccountNo(), LocalDate.now(), amount);
            ObjectInputStream objectInputStream = null;
            Path filePath = Paths.get("Account");
            ArrayList<AccountTransactionWrapper> accounts = new ArrayList<>();

            // Check if the file already exists
            if (Files.exists(filePath)) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(filePath.toString()));
                    accounts = (ArrayList<AccountTransactionWrapper>) objectInputStream.readObject();
                } catch (EOFException ex) {
                    System.out.println("End of file reached.");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            // Find the account in the ArrayList and append the transaction
            boolean accountFound = false;
            for (AccountTransactionWrapper accountWrapper : accounts) {
                if (accountWrapper.getAccount().getAccountNo() == getAccountNo()) {
                    accountWrapper.getTransactions().add(trans);
                    accountWrapper.getAccount().setBalance(balance);
                    accountFound = true;
                    break;
                }
            }

            // If the account was not found, create a new AccountTransactionWrapper
            if (!accountFound) {
                AccountTransactionWrapper newAccountWrapper = new AccountTransactionWrapper(this);
                newAccountWrapper.getTransactions().add(trans);
                newAccountWrapper.getAccount().setBalance(balance);
                accounts.add(newAccountWrapper);
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                objectOutputStream.writeObject(accounts);
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public void transfer(double amount, Account account) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            account.balance += amount;
            Transfer trans = new Transfer("transfer", generate_Random.generateRandomTransactionID(), getAccountNo(),
                    account, LocalDate.now(), amount);
            ObjectInputStream objectInputStream = null;
            Path filePath = Paths.get("Account");
            ArrayList<AccountTransactionWrapper> accounts = new ArrayList<>();

            // Check if the file already exists
            if (Files.exists(filePath)) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(filePath.toString()));
                    accounts = (ArrayList<AccountTransactionWrapper>) objectInputStream.readObject();
                } catch (EOFException ex) {
                    System.out.println("End of file reached.");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            // Find the account in the ArrayList and append the transaction
            boolean accountFound = false;
            boolean accountFound1 = false;
            for (AccountTransactionWrapper accountWrapper : accounts) {
                if (accountWrapper.getAccount().getAccountNo() == getAccountNo()) {
                    accountWrapper.getTransactions().add(trans);
                    accountWrapper.getAccount().setBalance(balance);
                    accountFound = true;
                    break;
                }
            }
            for (AccountTransactionWrapper accountWrapper1 : accounts) {
                if (accountWrapper1.getAccount().getAccountNo() == account.getAccountNo()) {
                    accountWrapper1.getTransactions().add(trans);
                    accountWrapper1.getAccount().setBalance(account.balance);
                    accountFound1 = true;
                    break;
                }
            }

            // If the account was not found, create a new AccountTransactionWrapper
            if (!accountFound) {
                AccountTransactionWrapper newAccountWrapper = new AccountTransactionWrapper(this);
                newAccountWrapper.getTransactions().add(trans);
                newAccountWrapper.getAccount().setBalance(balance);
                accounts.add(newAccountWrapper);
            }
            if (!accountFound1) {
                AccountTransactionWrapper newAccountWrapper1 = new AccountTransactionWrapper(this);
                newAccountWrapper1.getTransactions().add(trans);
                newAccountWrapper1.getAccount().setBalance(account.balance);
                accounts.add(newAccountWrapper1);
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                objectOutputStream.writeObject(accounts);
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Login getLogin() {
        return login;
    }
}
