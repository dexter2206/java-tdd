package bankaccount;

public class BankAccount {

    private double balance;
    private boolean opened;

    public BankAccount() {
        balance = 0.0;
        opened = true;
    }

    public BankAccount(double initialMoney) {
        if(initialMoney < 0) {
            throw new IllegalArgumentException(
                    "Account can start only with nonnegative amount of money.");
        }
        balance = initialMoney;
        opened = true;
    }

    public void deposit(double amount) {
        if(!isOpened()) {
            throw new RuntimeException("Account closed.");
        }
        if(amount <= 0.0) {
            throw new IllegalArgumentException("Amount has to be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if(!isOpened()) {
            throw new RuntimeException("Account closed.");
        }
        if(amount <= 0.0) {
            throw new IllegalArgumentException("Amount has to be positive");
        }
        if(amount > balance) {
            throw new RuntimeException("You can't withdraw that much.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isOpened() {
        return opened;
    }

    public void close() {
        if(!isOpened()) {
            throw new RuntimeException("Already closed.");
        }
        opened = false;
        balance = 0.0;
    }
}
