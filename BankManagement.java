
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BankManagement {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        mainPage myProject = new mainPage();
        myProject.setVisible(true);
    }
}

class mainPage extends JFrame implements ActionListener {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public mainPage() {
        setTitle("Welcome!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setBackground(Color.gray);

        JButton customerButton = new JButton("Customer");
        customerButton.setBounds(200, 275, 150, 30);
        customerButton.setFont(new Font(null, Font.BOLD, 15));
        customerButton.addActionListener(this);
        JButton managerButton = new JButton("Manager");
        managerButton.setBounds(385, 275, 150, 30);
        managerButton.setFont(new Font(null, Font.BOLD, 15));
        managerButton.addActionListener(this);

        JLabel online = new JLabel("ONLINE");
        online.setBounds(20, 125, 100, 35);
        online.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        JLabel Banking = new JLabel("BANKING");
        Banking.setBounds(20, 175, 150, 35);
        Banking.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        add(online);
        add(Banking);

        add(customerButton);
        add(managerButton);
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PC\\Downloads\\banking.jpg");
        setIconImage(logoIcon.getImage());

        JLabel backgroundLabel = new JLabel(logoIcon);
        backgroundLabel.setBounds(165, 0, getWidth(), getHeight());
        add(backgroundLabel);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Customer")) {
            LoginPage l = new LoginPage();
            l.setVisible(true);
        } else if (actionCommand.equals("Manager")) {
            LoginPageManager l = new LoginPageManager();
            l.setVisible(true);
        }
    }
}

class LoginPage extends JFrame implements ActionListener {

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTextField usernameField;
    private JPasswordField PasswordField;
    private JLabel messageLabel;
    private JTextField textField1;

    public LoginPage() {
        setTitle("WELCOME TO RC BANK!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds(150, 125, 250, 25);
        usernameField.setBackground(Color.WHITE);
        PasswordField = new JPasswordField();
        PasswordField.setBounds(150, 175, 250, 25);
        PasswordField.setBackground(Color.WHITE);
        add(usernameField);
        add(PasswordField);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PC\\Downloads\\login.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(20, 10, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        add(logoLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel UsernameLabel = new JLabel("Username: ");
        JLabel PasswordLabel = new JLabel("Password: ");
        messageLabel = new JLabel("LOGIN");

        UsernameLabel.setBounds(50, 125, 75, 25);
        PasswordLabel.setBounds(50, 175, 75, 25);
        messageLabel.setBounds(125, 30, 250, 30);
        messageLabel.setFont(new Font(null, Font.BOLD, 35));

        add(UsernameLabel);
        add(PasswordLabel);
        add(messageLabel);

        JButton LoginButton = new JButton("Login");
        LoginButton.setBounds(150, 225, 250, 25);
        LoginButton.addActionListener(this);

        JButton SignButton = new JButton("Create an Account");
        SignButton.setBounds(150, 275, 250, 25);
        SignButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setBounds(450, 300, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        add(LoginButton);
        add(SignButton);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Create an Account")) {
            Signup sign = new Signup();
            sign.setVisible(true);

        } else if (actionCommand.equals("Login")) {
            String username = usernameField.getText();
            String password = String.valueOf(PasswordField.getPassword());
            ObjectInputStream objectInputStream = null;
            Path p;
            p = Paths.get("Account");
            if (!Files.exists(p))
                new ArrayList<AccountTransactionWrapper>();
            ArrayList<AccountTransactionWrapper> accounts = null;
            boolean loginSuccessful = false;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("Account"));
                while (true) {
                    accounts = (ArrayList<AccountTransactionWrapper>) objectInputStream.readObject();
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getAccount().getLogin().getUsername().equals(username)
                                && accounts.get(i).getAccount().getLogin().getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(this, "Login Successful!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            MainMenu dashboard = new MainMenu();
                            dashboard.setCurrentAccount(accounts.get(i).getAccount());
                            dashboard.setVisible(true);
                            loginSuccessful = true;
                            break;
                        }
                    }
                }
            } catch (EOFException ex) {
                System.out.println("End of file reached.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (!loginSuccessful) {
                JOptionPane.showMessageDialog(this, "Login Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                PasswordField.setText("");
            }

        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class Signup extends JFrame implements ActionListener {
    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cityField;
    private JTextField houseField;
    private JTextField streetField;
    private JTextField usernameField;
    private JTextField passwordField;

    public Signup() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(125, 70, 200, 25);
        nameField.setBackground(Color.WHITE);
        phoneField = new JTextField();
        phoneField.setBounds(125, 105, 200, 25);
        phoneField.setBackground(Color.WHITE);
        cityField = new JTextField();
        cityField.setBounds(125, 140, 200, 25);
        cityField.setBackground(Color.WHITE);
        houseField = new JTextField();
        houseField.setBounds(125, 175, 200, 25);
        houseField.setBackground(Color.WHITE);
        streetField = new JTextField();
        streetField.setBounds(125, 210, 200, 25);
        streetField.setBackground(Color.WHITE);
        usernameField = new JTextField();
        usernameField.setBounds(125, 245, 200, 25);
        usernameField.setBackground(Color.WHITE);
        passwordField = new JTextField();
        passwordField.setBounds(125, 280, 200, 25);
        passwordField.setBackground(Color.WHITE);
        add(nameField);
        add(phoneField);
        add(cityField);
        add(houseField);
        add(streetField);
        add(usernameField);
        add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        JLabel PhoneLabel = new JLabel("Phone No: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel HouseLabel = new JLabel("House No: ");
        JLabel StreetLabel = new JLabel("Street No: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel message = new JLabel("Create an Account");

        nameLabel.setBounds(50, 70, 75, 25);
        PhoneLabel.setBounds(50, 105, 75, 25);
        cityLabel.setBounds(50, 140, 75, 25);
        HouseLabel.setBounds(50, 175, 75, 25);
        StreetLabel.setBounds(50, 210, 75, 25);
        usernameLabel.setBounds(50, 245, 75, 25);
        passwordLabel.setBounds(50, 280, 75, 25);
        message.setBounds(50, 20, 250, 25);
        message.setFont(new Font(null, Font.BOLD, 20));

        add(nameLabel);
        add(PhoneLabel);
        add(cityLabel);
        add(HouseLabel);
        add(StreetLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(message);

        JButton RegisterButton = new JButton("Register");
        RegisterButton.setBounds(225, 315, 100, 25);
        RegisterButton.addActionListener(this);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(125, 315, 100, 25);
        BackButton.addActionListener(this);

        add(RegisterButton);
        add(BackButton);

    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Register")) {
            String name = nameField.getText();
            long phoneNumber = Long.parseLong(phoneField.getText());
            String city = cityField.getText();
            int houseNo = Integer.parseInt(houseField.getText());
            int streetNo = Integer.parseInt(streetField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            ArrayList<AccountTransactionWrapper> accounts = new ArrayList<>();
            Account a = new Account(name, phoneNumber, new Address(city, houseNo, streetNo),
                    generate_Random.generateRandomAccountNo(), new Login(username, password));

            accounts.add(new AccountTransactionWrapper(a, a.getTransaction()));
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Account"))) {
                objectOutputStream.writeObject(accounts);
                objectOutputStream.close();
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "You are Registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
            toBack();
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class MainMenu extends JFrame implements ActionListener {
    private Account currentAccount;
    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 500;
    public static final int HEIGHT = 1500;

    public MainMenu() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JLabel messageLabel = new JLabel("Welcome to Online Banking!");
        messageLabel.setBounds(125, 20, 400, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));
        add(messageLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JButton myAccount = new JButton("My Account");
        myAccount.setBounds(125, 75, 250, 25);
        myAccount.addActionListener(this);
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PC\\Downloads\\account.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(30, 65, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        add(logoLabel);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(125, 150, 250, 25);
        withdrawButton.addActionListener(this);
        ImageIcon logoIcon1 = new ImageIcon("C:\\Users\\PC\\Downloads\\withdraw.png");
        JLabel logoLabel1 = new JLabel(logoIcon1);
        logoLabel1.setBounds(30, 140, logoIcon1.getIconWidth(), logoIcon1.getIconHeight());
        add(logoLabel1);
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(125, 225, 250, 25);
        depositButton.addActionListener(this);
        ImageIcon logoIcon2 = new ImageIcon("C:\\Users\\PC\\Downloads\\deposit.png");
        JLabel logoLabel2 = new JLabel(logoIcon2);
        logoLabel2.setBounds(30, 215, logoIcon2.getIconWidth(), logoIcon2.getIconHeight());
        add(logoLabel2);
        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(125, 300, 250, 25);
        transferButton.addActionListener(this);
        ImageIcon logoIcon3 = new ImageIcon("C:\\Users\\PC\\Downloads\\transfer.png");
        JLabel logoLabel3 = new JLabel(logoIcon3);
        logoLabel3.setBounds(30, 290, logoIcon3.getIconWidth(), logoIcon3.getIconHeight());
        add(logoLabel3);
        JButton transactionButton = new JButton("Transaction History");
        transactionButton.setBounds(125, 375, 250, 25);
        transactionButton.addActionListener(this);
        ImageIcon logoIcon4 = new ImageIcon("C:\\Users\\PC\\Downloads\\history.png");
        JLabel logoLabel4 = new JLabel(logoIcon4);
        logoLabel4.setBounds(30, 365, logoIcon4.getIconWidth(), logoIcon4.getIconHeight());
        add(logoLabel4);
        JButton loanButton = new JButton("Loan");
        loanButton.setBounds(125, 450, 250, 25);
        loanButton.addActionListener(this);
        ImageIcon logoIcon5 = new ImageIcon("C:\\Users\\PC\\Downloads\\loan1.png");
        JLabel logoLabel5 = new JLabel(logoIcon5);
        logoLabel5.setBounds(30, 440, logoIcon5.getIconWidth(), logoIcon5.getIconHeight());
        add(logoLabel5);
        JButton creditCard = new JButton("Credit Card");
        creditCard.setBounds(125, 525, 250, 25);
        creditCard.addActionListener(this);
        ImageIcon logoIcon6 = new ImageIcon("C:\\Users\\PC\\Downloads\\credit.png");
        JLabel logoLabel6 = new JLabel(logoIcon6);
        logoLabel6.setBounds(30, 515, logoIcon6.getIconWidth(), logoIcon6.getIconHeight());
        add(logoLabel6);
        JButton signOut = new JButton("Sign Out");
        signOut.setBounds(125, 600, 250, 25);
        signOut.addActionListener(this);
        ImageIcon logoIcon7 = new ImageIcon("C:\\Users\\PC\\Downloads\\signOut.png");
        JLabel logoLabel7 = new JLabel(logoIcon7);
        logoLabel7.setBounds(30, 590, logoIcon7.getIconWidth(), logoIcon7.getIconHeight());
        add(logoLabel7);

        add(myAccount);
        add(withdrawButton);
        add(depositButton);
        add(transferButton);
        add(transactionButton);
        add(loanButton);
        add(creditCard);
        add(signOut);
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("My Account")) {
            myAccount m = new myAccount(currentAccount);
            m.setVisible(true);
        } else if (actionCommand.equals("Withdraw")) {
            withdrawPage w = new withdrawPage(currentAccount);
            w.setVisible(true);
        } else if (actionCommand.equals("Deposit")) {
            depositPage d = new depositPage(currentAccount);
            d.setVisible(true);
        } else if (actionCommand.equals("Transfer")) {
            transferPage t = new transferPage(currentAccount);
            t.setVisible(true);
        } else if (actionCommand.equals("Transaction History")) {
            transactionHistoryPage th = new transactionHistoryPage(currentAccount);
            th.setVisible(true);
        } else if (actionCommand.equals("Sign Out")) {
            toBack();
        } else if (actionCommand.equals("Loan")) {
            LoanPage l = new LoanPage(currentAccount);
            l.setVisible(true);
        } else if (actionCommand.equals("Credit Card")) {
            creditCardPage c = new creditCardPage(currentAccount);
            c.setVisible(true);
        }
    }
}

class SignupManager extends JFrame implements ActionListener {
    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cityField;
    private JTextField houseField;
    private JTextField streetField;
    private JTextField usernameField;
    private JTextField passwordField;

    public SignupManager() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(125, 70, 200, 25);
        nameField.setBackground(Color.WHITE);
        phoneField = new JTextField();
        phoneField.setBounds(125, 105, 200, 25);
        phoneField.setBackground(Color.WHITE);
        cityField = new JTextField();
        cityField.setBounds(125, 140, 200, 25);
        cityField.setBackground(Color.WHITE);
        houseField = new JTextField();
        houseField.setBounds(125, 175, 200, 25);
        houseField.setBackground(Color.WHITE);
        streetField = new JTextField();
        streetField.setBounds(125, 210, 200, 25);
        streetField.setBackground(Color.WHITE);
        usernameField = new JTextField();
        usernameField.setBounds(125, 245, 200, 25);
        usernameField.setBackground(Color.WHITE);
        passwordField = new JTextField();
        passwordField.setBounds(125, 280, 200, 25);
        passwordField.setBackground(Color.WHITE);
        add(nameField);
        add(phoneField);
        add(cityField);
        add(houseField);
        add(streetField);
        add(usernameField);
        add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel nameLabel = new JLabel("Name: ");
        JLabel PhoneLabel = new JLabel("Phone No: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel HouseLabel = new JLabel("House No: ");
        JLabel StreetLabel = new JLabel("Street No: ");
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel message = new JLabel("Create an Account");

        nameLabel.setBounds(50, 70, 75, 25);
        PhoneLabel.setBounds(50, 105, 75, 25);
        cityLabel.setBounds(50, 140, 75, 25);
        HouseLabel.setBounds(50, 175, 75, 25);
        StreetLabel.setBounds(50, 210, 75, 25);
        usernameLabel.setBounds(50, 245, 75, 25);
        passwordLabel.setBounds(50, 280, 75, 25);
        message.setBounds(50, 20, 250, 25);
        message.setFont(new Font(null, Font.BOLD, 20));

        add(nameLabel);
        add(PhoneLabel);
        add(cityLabel);
        add(HouseLabel);
        add(StreetLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(message);

        JButton RegisterButton = new JButton("Register");
        RegisterButton.setBounds(225, 315, 100, 25);
        RegisterButton.addActionListener(this);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(125, 315, 100, 25);
        BackButton.addActionListener(this);

        add(RegisterButton);
        add(BackButton);

    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Register")) {
            String name = nameField.getText();
            long phoneNumber = Long.parseLong(phoneField.getText());
            String city = cityField.getText();
            int houseNo = Integer.parseInt(houseField.getText());
            int streetNo = Integer.parseInt(streetField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            ArrayList<Manager> managers = new ArrayList<>();

            managers.add(new Manager(name, phoneNumber, new Address(city, houseNo, streetNo),
                    new Login(username, password)));

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Manager"))) {
                objectOutputStream.writeObject(managers);
                objectOutputStream.close();
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "You are Registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
            toBack();
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class LoginPageManager extends JFrame implements ActionListener {

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTextField usernameField;
    private JPasswordField PasswordField;
    private JLabel messageLabel;
    private JTextField textField1;

    public LoginPageManager() {
        setTitle("WELCOME TO RC BANK!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        usernameField = new JTextField();
        usernameField.setBounds(150, 125, 250, 25);
        usernameField.setBackground(Color.WHITE);
        PasswordField = new JPasswordField();
        PasswordField.setBounds(150, 175, 250, 25);
        PasswordField.setBackground(Color.WHITE);
        add(usernameField);
        add(PasswordField);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PC\\Downloads\\login.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(20, 10, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        add(logoLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel UsernameLabel = new JLabel("Username: ");
        JLabel PasswordLabel = new JLabel("Password: ");
        messageLabel = new JLabel("LOGIN");

        UsernameLabel.setBounds(50, 125, 75, 25);
        PasswordLabel.setBounds(50, 175, 75, 25);
        messageLabel.setBounds(125, 30, 250, 30);
        messageLabel.setFont(new Font(null, Font.BOLD, 35));

        add(UsernameLabel);
        add(PasswordLabel);
        add(messageLabel);

        JButton LoginButton = new JButton("Login");
        LoginButton.setBounds(150, 225, 250, 25);
        LoginButton.addActionListener(this);

        JButton SignButton = new JButton("Create an Account");
        SignButton.setBounds(150, 275, 250, 25);
        SignButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setBounds(450, 300, 100, 25);
        backButton.addActionListener(this);
        add(backButton);

        add(LoginButton);
        add(SignButton);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Create an Account")) {
            SignupManager sign = new SignupManager();
            sign.setVisible(true);

        } else if (actionCommand.equals("Login")) {
            String username = usernameField.getText();
            String password = String.valueOf(PasswordField.getPassword());
            ObjectInputStream objectInputStream = null;
            Path p;
            p = Paths.get("Manager");
            if (!Files.exists(p))
                new ArrayList<Manager>();
            ArrayList<Manager> managers = null;
            boolean loginSuccessful = false;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("Manager"));
                while (true) {
                    managers = (ArrayList<Manager>) objectInputStream.readObject();
                    for (int i = 0; i < managers.size(); i++) {
                        if (managers.get(i).getLogin().getUsername().equals(username)
                                && managers.get(i).getLogin().getPassword().equals(password)) {
                            loginSuccessful = true;
                            JOptionPane.showMessageDialog(this, "Login Successful!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            managerMainPage m = new managerMainPage(managers.get(i));
                            m.setVisible(true);
                            break;
                        }
                    }
                }
            } catch (EOFException ex) {
                System.out.println("End of file reached.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (!loginSuccessful) {
                // Show error message and clear fields
                JOptionPane.showMessageDialog(this, "Login Failed!", "Error", JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                PasswordField.setText("");
            }

        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class managerMainPage extends JFrame implements ActionListener {
    private Manager currentManager;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private JLabel messageLabel;

    public managerMainPage(Manager currentManager) {
        this.currentManager = currentManager;
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton approveButton = new JButton("Approve Loan");
        approveButton.setBounds(100, 100, 200, 30);
        approveButton.addActionListener(this);
        JButton deleteButton = new JButton("Delete Account");
        deleteButton.setBounds(100, 200, 200, 30);
        deleteButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setBounds(225, 300, 100, 25);
        backButton.addActionListener(this);

        add(approveButton);
        add(deleteButton);
        add(backButton);
        messageLabel = new JLabel("Welcome to Online Banking");
        messageLabel.setBounds(25, 300, 200, 25);
        add(messageLabel);

    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Delete Account")) {
            DeleteAccountPage d = new DeleteAccountPage(currentManager);
            d.setVisible(true);
        } else if (actionCommand.equals("Approve Loan")) {
            approveLoanPage a = new approveLoanPage(currentManager);
            a.setVisible(true);

        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class DeleteAccountPage extends JFrame implements ActionListener {
    private JTextField accountNumberField;
    private Manager currentManger;

    public DeleteAccountPage(Manager currentManger) {
        this.currentManger = currentManger;
        setTitle("Delete Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);

        JLabel titleLabel = new JLabel("Delete Account");
        titleLabel.setBounds(140, 10, 150, 30);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        add(titleLabel);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(30, 60, 120, 25);
        add(accountNumberLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(150, 60, 200, 25);
        add(accountNumberField);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 100, 100, 25);
        deleteButton.addActionListener(this);
        add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(225, 300, 100, 25);
        backButton.addActionListener(this);

        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        long accountNumber = Long.parseLong(accountNumberField.getText());

        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Delete")) {
            ObjectInputStream objectInputStream = null;
            Path p;
            p = Paths.get("Account");
            if (!Files.exists(p))
                new ArrayList<AccountTransactionWrapper>();
            ArrayList<AccountTransactionWrapper> accounts = null;
            boolean deletionSuccessful = false;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("Account"));
                while (true) {
                    accounts = (ArrayList<AccountTransactionWrapper>) objectInputStream.readObject();
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accountNumber == accounts.get(i).getAccount().getAccountNo()) {
                            accounts.remove(accounts.get(i));
                            deletionSuccessful = true;
                            break;
                        }
                    }
                }
            } catch (EOFException ex) {
                System.out.println("End of file reached.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (deletionSuccessful) {
                JOptionPane.showMessageDialog(this, "Account successfully deleted!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                accountNumberField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Account deletion Unsuccessful!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                accountNumberField.setText("");
            }
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class approveLoanPage extends JFrame implements ActionListener {
    private JTextField accountNumberField;
    private Manager currentManger;
    private JTextArea loanArea;

    public approveLoanPage(Manager currentManger) {
        this.currentManger = currentManger;
        setTitle("Approve Loan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);

        JLabel titleLabel = new JLabel("Approve Loan");
        titleLabel.setBounds(140, 10, 150, 30);
        titleLabel.setFont(new Font(null, Font.BOLD, 20));
        add(titleLabel);

        loanArea = new JTextArea("");
        loanArea.setBounds(25, 50, 300, 300);
        loanArea.setEditable(false);
        add(loanArea);
        ArrayList<loan> loans = new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("loan"))) {
            while (true) {
                loans = (ArrayList<loan>) objectInputStream.readObject();
                StringBuilder transactionHistory = new StringBuilder();

                for (loan loan1 : loans) {
                    if (!loan1.isApprove()) {
                        Account account = loan1.getAccount();
                        transactionHistory.append("Account Number: ").append(account.getAccountNo()).append("\n");
                        transactionHistory.append("Customer Name: ").append(account.getName()).append("\n");
                        transactionHistory.append("\n");
                    }
                }

                loanArea.setText(transactionHistory.toString());
            }
        } catch (EOFException ex) {
            System.out.println("End of file reached.");
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

        JButton approveButton = new JButton("Approve All");
        approveButton.setBounds(100, 400, 150, 25);
        approveButton.addActionListener(this);
        add(approveButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(275, 400, 100, 25);
        backButton.addActionListener(this);

        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Approve All")) {
            ObjectInputStream objectInputStream = null;
            Path p;
            p = Paths.get("loan");
            if (!Files.exists(p))
                new ArrayList<loan>();
            ArrayList<loan> loans = null;
            boolean loanSuccessful = false;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("loan"));
                while (true) {
                    loans = (ArrayList<loan>) objectInputStream.readObject();
                    for (int i = 0; i < loans.size(); i++) {
                        if (loans.get(i).isApprove() == false) {
                            loans.get(i).setApprove(true);
                            loanSuccessful = true;
                        }
                    }

                }
            } catch (EOFException ex) {
                System.out.println("End of file reached.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (loanSuccessful) {
                JOptionPane.showMessageDialog(this, "Loans Approved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loanArea.setText("");
            }
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class myAccount extends JFrame implements ActionListener {
    private Account currentAccount;

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    public myAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("My Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel NameLabel = new JLabel("Name: ");
        JLabel phoneLabel = new JLabel("Phone No: ");
        JLabel accountNoLabel = new JLabel("Account No: ");
        JLabel BalanceLabel = new JLabel("Balance: ");
        JLabel messageLabel = new JLabel("Account Details");

        NameLabel.setBounds(50, 100, 75, 25);
        phoneLabel.setBounds(50, 150, 75, 25);
        accountNoLabel.setBounds(50, 200, 75, 25);
        BalanceLabel.setBounds(50, 250, 75, 25);
        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        JLabel NameLabel1 = new JLabel(currentAccount.getName());
        JLabel phoneLabel1 = new JLabel(String.valueOf(currentAccount.getPhone()));
        JLabel accountNoLabel1 = new JLabel(String.valueOf(currentAccount.getAccountNo()));
        JLabel BalanceLabel1 = new JLabel(String.valueOf(currentAccount.getBalance()));

        NameLabel1.setBounds(125, 100, 75, 25);
        phoneLabel1.setBounds(125, 150, 75, 25);
        accountNoLabel1.setBounds(125, 200, 200, 25);
        BalanceLabel1.setBounds(125, 250, 75, 25);

        add(NameLabel);
        add(phoneLabel);
        add(accountNoLabel);
        add(BalanceLabel);
        add(messageLabel);
        add(NameLabel1);
        add(phoneLabel1);
        add(accountNoLabel1);
        add(BalanceLabel1);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 300, 100, 25);
        BackButton.addActionListener(this);

        add(BackButton);
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class withdrawPage extends JFrame implements ActionListener {
    private Account currentAccount;

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JTextField amountField;
    private JLabel transactionLabel;

    public withdrawPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Withdraw");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        amountField = new JTextField();
        amountField.setBounds(50, 150, 200, 25);
        amountField.setBackground(Color.WHITE);
        add(amountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel amountLabel = new JLabel("Enter amount to withdraw: ");
        JLabel messageLabel = new JLabel("Cash Withdrawal");

        amountLabel.setBounds(50, 100, 200, 25);
        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        add(amountLabel);
        add(messageLabel);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(75, 200, 100, 25);
        withdrawButton.addActionListener(this);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 300, 100, 25);
        BackButton.addActionListener(this);

        add(BackButton);
        add(withdrawButton);
        transactionLabel = new JLabel();
        transactionLabel.setBounds(50, 300, 200, 25);
        add(transactionLabel);

    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Withdraw")) {
            double amount1 = Double.parseDouble(amountField.getText());
            if (currentAccount.withdraw(amount1)) {
                transactionLabel.setText("Successfully Withdrawn!");
            } else {
                transactionLabel.setText("Withdrawal unsuccessful!");
            }
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class depositPage extends JFrame implements ActionListener {
    private Account currentAccount;

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JTextField amountField;
    private JLabel transactionLabel;

    public depositPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Deposit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        amountField = new JTextField();
        amountField.setBounds(50, 150, 200, 25);
        amountField.setBackground(Color.WHITE);
        add(amountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel amountLabel = new JLabel("Enter amount to deposit: ");
        JLabel messageLabel = new JLabel("Cash Deposit");

        amountLabel.setBounds(50, 100, 150, 25);
        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        add(amountLabel);
        add(messageLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(75, 200, 100, 25);
        depositButton.addActionListener(this);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 300, 100, 25);
        BackButton.addActionListener(this);

        add(BackButton);
        add(depositButton);
        transactionLabel = new JLabel();
        transactionLabel.setBounds(50, 300, 200, 25);
        add(transactionLabel);

    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Deposit")) {
            double amount1 = Double.parseDouble(amountField.getText());
            currentAccount.deposit(amount1);
            transactionLabel.setText("Successfully deposited!");
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class transferPage extends JFrame implements ActionListener {
    private Account currentAccount;

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JTextField amountField;
    private JTextField accountField;
    private JLabel transactionLabel;

    public transferPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Deposit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        amountField = new JTextField();
        amountField.setBounds(50, 100, 200, 25);
        amountField.setBackground(Color.WHITE);
        add(amountField);
        accountField = new JTextField();
        accountField.setBounds(50, 200, 200, 25);
        accountField.setBackground(Color.WHITE);
        add(accountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel amountLabel = new JLabel("Enter amount to transfer: ");
        JLabel accountLabel = new JLabel("Enter Account No: ");
        JLabel messageLabel = new JLabel("Cash Transfer");

        amountLabel.setBounds(50, 50, 200, 25);
        accountLabel.setBounds(50, 150, 200, 25);
        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        add(amountLabel);
        add(messageLabel);
        add(accountLabel);

        JButton depositButton = new JButton("Transfer");
        depositButton.setBounds(75, 250, 100, 25);
        depositButton.addActionListener(this);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 300, 100, 25);
        BackButton.addActionListener(this);

        add(BackButton);
        add(depositButton);
        transactionLabel = new JLabel();
        transactionLabel.setBounds(50, 300, 200, 25);
        add(transactionLabel);

    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Transfer")) {
            long accountNum = Long.parseLong(accountField.getText());
            double amount = Double.parseDouble(amountField.getText());
            ObjectInputStream objectInputStream = null;
            Path p;
            p = Paths.get("Account");
            if (!Files.exists(p))
                new ArrayList<AccountTransactionWrapper>();
            ArrayList<AccountTransactionWrapper> accounts = null;
            boolean transferSuccessful = false;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("Account"));
                while (true) {
                    accounts = (ArrayList<AccountTransactionWrapper>) objectInputStream.readObject();
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getAccount().getAccountNo() == accountNum) {
                            currentAccount.transfer(amount, accounts.get(i).getAccount());
                            transactionLabel.setText("Transferred successfully!");
                            transferSuccessful = true;
                            break;
                        }

                    }
                }
            } catch (EOFException ex) {
                System.out.println("End of file reached.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (!transferSuccessful) {
                transactionLabel.setText("Transfer unsuccessful!");
                amountField.setText("");
                accountField.setText("");
            }

        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class transactionHistoryPage extends JFrame implements ActionListener {
    private Account currentAccount;
    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JLabel transactionLabel;

    public transactionHistoryPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Transaction History");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel messageLabel = new JLabel("Transaction History");

        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        add(messageLabel);

        JTextArea transactions = new JTextArea();
        transactions.setEditable(false);
        transactions.setBounds(20, 50, 550, 400);

        StringBuilder transactionHistory = new StringBuilder();
        ArrayList<Transaction> accountTransactions = currentAccount.getTransaction();
        for (Transaction transaction : accountTransactions) {
            transactionHistory.append(transaction.transaction_history()).append("\n");
        }
        transactions.setText(transactionHistory.toString());
        add(transactions);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(450, 500, 100, 20);
        BackButton.addActionListener(this);

        add(BackButton);

    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class getLoanPage extends JFrame implements ActionListener {
    private Account currentAccount;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private JTextField accountField;
    private JTextField loanAmountField;

    private JLabel messageLabel;
    private JLabel messageLabel2;
    private JLabel interestRateLabel1;
    private JLabel repaymentPeriodLabel1;
    private JLabel startDateLabel1;
    private JLabel loanIDLabel1;

    public getLoanPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Loan Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        accountField = new JTextField();
        accountField.setBounds(175, 100, 100, 25);
        accountField.setBackground(Color.WHITE);
        loanAmountField = new JTextField();
        loanAmountField.setBounds(175, 150, 100, 25);
        loanAmountField.setBackground(Color.WHITE);

        add(accountField);
        add(loanAmountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel loanIDLabel = new JLabel("Loan ID: ");
        loanIDLabel1 = new JLabel();
        JLabel accountLabel = new JLabel("Account: ");
        JLabel loanAmountLabel = new JLabel("Loan Amount: ");
        JLabel interestRateLabel = new JLabel("Interest Rate: ");
        interestRateLabel1 = new JLabel("0.05");
        JLabel repaymentPeriodLabel = new JLabel("Repayment Period: ");
        repaymentPeriodLabel1 = new JLabel();
        JLabel startDateLabel = new JLabel("Start Date: ");
        startDateLabel1 = new JLabel();
        messageLabel = new JLabel("Loan Application");
        messageLabel2 = new JLabel();

        loanIDLabel.setBounds(50, 50, 75, 25);
        loanIDLabel1.setBounds(175, 50, 75, 25);
        accountLabel.setBounds(50, 100, 75, 25);

        loanAmountLabel.setBounds(50, 150, 75, 25);
        interestRateLabel.setBounds(50, 200, 75, 25);
        interestRateLabel1.setBounds(175, 200, 75, 25);
        repaymentPeriodLabel.setBounds(50, 250, 75, 25);
        repaymentPeriodLabel1.setBounds(175, 250, 75, 25);
        startDateLabel.setBounds(50, 300, 75, 25);
        startDateLabel1.setBounds(175, 300, 75, 25);
        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));
        messageLabel2.setBounds(50, 400, 250, 25);

        add(loanIDLabel);
        add(loanIDLabel1);
        add(accountLabel);
        add(loanAmountLabel);
        add(interestRateLabel);
        add(interestRateLabel1);
        add(repaymentPeriodLabel);
        add(repaymentPeriodLabel1);
        add(startDateLabel);
        add(startDateLabel1);
        add(messageLabel);
        add(messageLabel2);

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(225, 350, 100, 25);
        applyButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setBounds(125, 350, 100, 25);
        backButton.addActionListener(this);

        add(applyButton);
        add(backButton);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Apply")) {
            long account = Long.parseLong(accountField.getText());
            double loanAmount = Double.parseDouble(loanAmountField.getText());
            long loanID = generate_Random.generateRandomTransactionID();
            loanIDLabel1.setText(String.valueOf(loanID));
            double interestRate = 0.05;
            interestRateLabel1.setText(String.valueOf(interestRate));
            int repaymentPeriod = 36;
            repaymentPeriodLabel1.setText(String.valueOf(repaymentPeriod));
            LocalDate startDate = LocalDate.now();
            startDateLabel1.setText(String.valueOf(startDate));

            loan newLoan = new loan(loanID, currentAccount, loanAmount, interestRate, repaymentPeriod, startDate);
            ArrayList<loan> loans = new ArrayList<>();

            loan l = new loan(loanID, currentAccount, loanAmount, interestRate, repaymentPeriod, startDate);

            loans.add(l);

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("loan"))) {
                objectOutputStream.writeObject(loans);
                objectOutputStream.close();
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            messageLabel2.setText("Your monthly payment: " + String.valueOf(l.calculateMonthlyPayment()));

        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class LoanPage extends JFrame implements ActionListener {
    private Account currentAccount;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    public LoanPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Loan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton getButton = new JButton("Get Loan");
        getButton.setBounds(100, 150, 200, 30);
        getButton.addActionListener(this);
        JButton payButton = new JButton("Pay Loan");
        payButton.setBounds(100, 200, 200, 30);
        payButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setBounds(225, 300, 100, 25);
        backButton.addActionListener(this);

        add(getButton);
        add(payButton);
        add(backButton);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PC\\Downloads\\loan.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(100, 10, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        add(logoLabel);

        JLabel loanApprove = new JLabel();
        loanApprove.setBounds(50, 300, 200, 20);
        add(loanApprove);
        JLabel loanApprove1 = new JLabel("Loan Request:");
        loanApprove1.setBounds(50, 250, 150, 15);
        add(loanApprove1);

        ObjectInputStream objectInputStream = null;
        Path p;
        p = Paths.get("loan");
        if (!Files.exists(p))
            new ArrayList<loan>();
        ArrayList<loan> loans = null;
        boolean loanSuccessful = false;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("loan"));
            while (true) {
                loans = (ArrayList<loan>) objectInputStream.readObject();
                for (int i = 0; i < loans.size(); i++) {
                    if (loans.get(i).getAccount().getAccountNo() == currentAccount.getAccountNo()) {
                        if (!loans.get(i).isApprove()) {
                            loanApprove.setText("Your loan was approved!");
                            loanSuccessful = true;
                            break;
                        }
                    }

                }
            }
        } catch (EOFException ex) {
            System.out.println("End of file reached.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            objectInputStream.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (!loanSuccessful) {
            loanApprove.setText("Loan request is not approved yet!");
        }

    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Get Loan")) {
            getLoanPage g = new getLoanPage(currentAccount);
            g.setVisible(true);
        } else if (actionCommand.equals("Pay Loan")) {
            payLoanPage p = new payLoanPage(currentAccount);
            p.setVisible(true);
        } else if (actionCommand.equals("Back")) {
            MainMenu back = new MainMenu();
            toBack();
        }
    }
}

class payLoanPage extends JFrame implements ActionListener {
    private Account currentAccount;

    Scanner input = new Scanner(System.in);
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JTextField amountField;
    private JLabel transactionLabel;

    public payLoanPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Pay Loan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        amountField = new JTextField();
        amountField.setBounds(50, 150, 200, 25);
        amountField.setBackground(Color.WHITE);
        add(amountField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(null);

        JLabel amountLabel = new JLabel("Enter amount to deposit: ");
        JLabel messageLabel = new JLabel("Loan Payment");

        amountLabel.setBounds(50, 100, 150, 25);
        messageLabel.setBounds(50, 20, 250, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 20));

        add(amountLabel);
        add(messageLabel);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(75, 200, 100, 25);
        payButton.addActionListener(this);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 300, 100, 25);
        BackButton.addActionListener(this);

        add(BackButton);
        add(payButton);
        transactionLabel = new JLabel();
        transactionLabel.setBounds(50, 300, 200, 25);
        add(transactionLabel);

    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Pay")) {
            double pay = Double.parseDouble(amountField.getText());
            ObjectInputStream objectInputStream = null;
            Path p;
            p = Paths.get("loan");
            if (!Files.exists(p))
                new ArrayList<loan>();
            ArrayList<loan> loans = null;
            boolean paySuccessful = false;
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream("loan"));
                while (true) {
                    loans = (ArrayList<loan>) objectInputStream.readObject();
                    for (int i = 0; i < loans.size(); i++) {
                        if (loans.get(i).getAccount().getAccountNo() == currentAccount.getAccountNo()) {
                            if (!loans.get(i).paid(pay)) {
                                transactionLabel.setText("paid successfully!");
                                paySuccessful = true;
                                break;
                            }
                        }

                    }
                }
            } catch (EOFException ex) {
                System.out.println("End of file reached.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (!paySuccessful) {
                transactionLabel.setText("Loan payment completed!");
            }

        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}

class creditCardPage extends JFrame implements ActionListener {
    private Account currentAccount;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private JLabel messageLabel;

    public creditCardPage(Account currentAccount) {
        this.currentAccount = currentAccount;
        setTitle("Credit Card");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton getButton = new JButton("Activate Credit Card");
        getButton.setBounds(100, 100, 200, 30);
        getButton.addActionListener(this);
        JButton payButton = new JButton("Deactivate Credit Card");
        payButton.setBounds(100, 200, 200, 30);
        payButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setBounds(225, 300, 100, 25);
        backButton.addActionListener(this);

        add(getButton);
        add(payButton);
        add(backButton);
        messageLabel = new JLabel("Credit Card Activation");
        messageLabel.setBounds(25, 25, 200, 25);
        messageLabel.setFont(new Font(null, Font.BOLD, 30));
        add(messageLabel);

    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Activate Credit Card") || actionCommand.equals("Deactivate Credit Card")) {
            ObjectInputStream objectInputStream = null;
            Path filePath = Paths.get("credit");
            ArrayList<CreditCard> creditCards = new ArrayList<>();

            if (Files.exists(filePath)) {
                try {
                    objectInputStream = new ObjectInputStream(new FileInputStream(filePath.toString()));
                    creditCards = (ArrayList<CreditCard>) objectInputStream.readObject();
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
            boolean accountFound = false;
            for (CreditCard card : creditCards) {
                if (card.getCardHolderName().equals(currentAccount.getName())) {
                    if (actionCommand.equals("Activate Credit Card")) {
                        card.setActivate_deactivate(true);
                        JOptionPane.showMessageDialog(this, "Credit Card Activated!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (actionCommand.equals("Deactivate Credit Card")) {
                        card.setActivate_deactivate(false);
                        JOptionPane.showMessageDialog(this, "Credit Card Deactivated!", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    accountFound = true;
                    break;
                }
            }

            if (!accountFound) {
                CreditCard newCreditCard = new CreditCard(generate_Random.generateRandomAccountNo(),
                        currentAccount.getName(), "10-10-2030");
                if (actionCommand.equals("Activate Credit Card")) {
                    newCreditCard.setActivate_deactivate(true);
                    messageLabel.setText("Credit Card Activated!");
                } else if (actionCommand.equals("Deactivate Credit Card")) {
                    newCreditCard.setActivate_deactivate(false);
                    messageLabel.setText("Credit Card Deactivated!");
                }
                creditCards.add(newCreditCard);
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                objectOutputStream.writeObject(creditCards);
                System.out.println("Successfully written.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (actionCommand.equals("Back")) {
            toBack();
        }
    }
}
