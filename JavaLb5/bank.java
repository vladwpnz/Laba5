import java.util.List;

import java.util.ArrayList;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {
    private int accountNumber;
    private String accountName;
    private double balance;

    public BankAccount(int accountNumber, String accountName, double initialDeposit) throws NegativeAmountException {
        if (initialDeposit < 0) {
            throw new NegativeAmountException("Початковий депозит не може бути негативним.");
        }

        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) throws NegativeAmountException {
        if (amount < 0) {
            throw new NegativeAmountException("Сума депозиту не може бути від'ємною.");
        }

        this.balance += amount;
    }

    public void withdraw(double amount) throws NegativeAmountException, InsufficientFundsException {
        if (amount < 0) {
            throw new NegativeAmountException("Сума виведення не може бути від’ємною.");
        }

        if (amount > this.balance) {
            throw new InsufficientFundsException("Недостатньо коштів для виведення.");
        }

        this.balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountSummary() {
        return "Номер рахунку: " + accountNumber + "\nНомер рахунку: " + accountName + "\nБаланс: $" + balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }
}


class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountName, double initialDeposit) throws NegativeAmountException {
        int accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, accountName, initialDeposit);
        this.accounts.add(newAccount);
        return newAccount;
    }

    public BankAccount findAccount(int accountNumber) throws AccountNotFoundException {
        
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotFoundException("Рахунок з номером " + accountNumber + " не знайден.");
    }

    public void transferMoney(int fromAccountNumber, int toAccountNumber, double amount)
            throws NegativeAmountException, InsufficientFundsException, AccountNotFoundException {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    private int generateAccountNumber() {
        
        return accounts.size() + 1;
    }
}


