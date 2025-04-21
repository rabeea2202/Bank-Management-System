import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class loan implements Serializable {
    private long loanID;
    private Account account;
    private double loanAmount;
    private double interestRate;
    private int repaymentPeriod;
    private LocalDate startDate;
    private boolean approve;

    // Constructor with parameters
    public loan(long loanID, Account account, double loanAmount, double interestRate, int repaymentPeriod,
            LocalDate startDate) {
        this.loanID = loanID;
        this.account = account;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.repaymentPeriod = repaymentPeriod;
        this.startDate = startDate;
        this.approve = false;
    }

    // Copy constructor
    public loan(loan otherLoan) {
        this.loanID = otherLoan.loanID;
        this.account = otherLoan.account;
        this.loanAmount = otherLoan.loanAmount;
        this.interestRate = otherLoan.interestRate;
        this.repaymentPeriod = otherLoan.repaymentPeriod;
        this.startDate = otherLoan.startDate;
        this.approve = otherLoan.approve;
    }

    // Getters
    public Account getAccount() {
        return account;
    }

    public long getLoanID() {
        return loanID;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public int getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isApprove() {
        return approve;
    }

    // Setters
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    // Interest amount (basic)
    public double calculateInterest() {
        return loanAmount * interestRate;
    }

    // Monthly payment formula (EMI style)
    public double calculateMonthlyPayment() {
        double monthlyRate = interestRate / 12;
        double a = loanAmount * monthlyRate * Math.pow(1 + monthlyRate, repaymentPeriod);
        double b = Math.pow(1 + monthlyRate, repaymentPeriod) - 1;
        return a / b;
    }

    // Method to handle loan repayment
    public boolean paid(double amount) {
        if (loanAmount <= 0) {
            System.out.println("Loan already fully paid!");
            return true;
        }

        loanAmount -= amount;
        Path filePath = Paths.get("loan");
        ArrayList<loan> loans = new ArrayList<>();

        // Load existing loans
        if (Files.exists(filePath)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toString()))) {
                loans = (ArrayList<loan>) ois.readObject();
            } catch (EOFException e) {
                System.out.println("Reached end of loan file.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Update or add the current loan
        boolean accountFound = false;
        for (loan l : loans) {
            if (l.getAccount().getAccountNo() == this.getAccount().getAccountNo()) {
                l.setLoanAmount(this.loanAmount);
                accountFound = true;
                break;
            }
        }

        if (!accountFound) {
            loans.add(new loan(this));
        }

        // Save updated loan list
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath.toString()))) {
            oos.writeObject(loans);
            System.out.println("Loan info updated.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.loanAmount <= 0;
    }
}
