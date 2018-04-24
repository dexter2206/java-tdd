package bankaccount;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
public class BankAccountTest {

    @Parameters(method="getParams")
    @Test
    public void whenDepositingMoneyBalanceChanges(double amount) {
        BankAccount account = new BankAccount();
        account.deposit(amount);
        assertEquals(amount, account.getBalance(), 1e-10);
    }

    @Parameters({"1000,100,1100", "2100,20,2120"})
    @Test
    public void whenDepositingToNonemptyAccountBalanceChanges(double initialAmount, double depositAmount,
                                                              double expectedAmount ) {

        BankAccount account = new BankAccount(initialAmount);
        account.deposit(depositAmount);
        assertEquals(expectedAmount, account.getBalance(), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingZeroThrowsException() {
        BankAccount account = new BankAccount();
        account.deposit(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDepositingNegativeAmountThrowsException() {
        BankAccount account = new BankAccount();
        account.deposit(-10.0);
    }

    @Test(expected = RuntimeException.class)
    public void whenDepositingToClosedAccount() {
        BankAccount account = new BankAccount();
        account.close();
        account.deposit(1000.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithdrawingZeroThrowsException() {
        BankAccount account = new BankAccount();
        account.deposit(100.0);
        account.withdraw(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithdrawingNegativeAmountThrowsException() {
        BankAccount account = new BankAccount();
        account.deposit(100.0);
        account.withdraw(-10);
    }

    @Test(expected = RuntimeException.class)
    public void whenWithdrawingFromClosedAccount() {
        BankAccount account = new BankAccount();
        account.deposit(200.0);
        account.close();
        account.withdraw(100.0);
    }

    @Test
    public void whenWithdrawingFromAccountBalanceChanges() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.withdraw(21);
        assertEquals(79, account.getBalance(), 1e-10);
    }

    @Test(expected = RuntimeException.class)
    public void whenWithdrawingMoreThanBalanceThrowsException() {
        BankAccount account = new BankAccount();
        account.deposit(2000);
        account.withdraw(3000);
    }

    @Test
    public void whenAccountCreatedIsOpenedIsTrue() {
        BankAccount account = new BankAccount();
        assertTrue(account.isOpened());
    }

    @Test
    public void whenAccountClosedIsOpenedIsFalse() {
        BankAccount account = new BankAccount();
        account.close();
        assertFalse(account.isOpened());
    }

    @Test(expected = RuntimeException.class)
    public void whenClosingClosedAccountThrowsException() {
        BankAccount account = new BankAccount();
        account.close();
        account.close();
    }

    @Test
    public void whenGettingBalanceOfClosedAccountReturnsZero() {
        BankAccount account = new BankAccount(100);
        account.close();
        assertEquals(0, account.getBalance(), 1e-10);
    }

    @Test
    public void whenOperatingOnClosedAccountThrowsRuntimeException() {
        BankAccount account = new BankAccount(1000.0);
        account.close();
        try {
            account.deposit(100);
            throw new AssertionError("It didn't throw.");
        } catch(RuntimeException e) { }
        try {
            account.withdraw(100);
            throw new AssertionError("It didn't throw");
        } catch(RuntimeException e) { }
    }

    @Test
    public void whenConstructingWithParamSetsBalance() {
        BankAccount account = new BankAccount(2131.0);
        assertEquals(2131.0, account.getBalance(), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConstructingWithNegativeAmountThrowsException() {
        new BankAccount(-1000);
    }

    public String[] getParams() {
        return new String[] {"200", "300", "400"};
    }
}