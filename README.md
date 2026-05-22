# Bank Management System

A Java-based banking application that provides features for both customers and managers. It allows account creation, transactions, credit cards, loans, and management functionality.

## Prerequisites
- Java Development Kit (JDK) 11 or higher installed on your system.

## Setup & Compilation
To compile the application simply navigate to the root directory and run:

```bash
javac */*.java
```

## Running the Application
Since this application uses Java GUI (Swing), make sure you run it in a desktop environment. 
Execute the following command to start the app:

```bash
java core.BankManagement
```

## Features
- **Client End**: Includes Signup, Login, Profile Viewing, Transactions (deposit, withdraw, transfer).
- **Manager End**: Delete Accounts, Approve Loans, Oversee system.
- Persistent local file storage (via Object Serialization).
