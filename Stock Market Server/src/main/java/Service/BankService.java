package Service;

import Model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final static List<Account> accountList = new ArrayList<>();

    public void CreateAccount(Account Account1) {

        System.out.println("Account added " + Account1.getPlayerId());
        this.accountList.add(Account1);
        for (Account account : accountList) {
            System.out.println("accountList  " + account.getName());
        }
    }

    //deposit to an account
    public Boolean Deposit(int turn, Long playerId, String sender, BigDecimal amount) throws Exception {
        for (Account account : accountList) {
            if (playerId.equals(account.getPlayerId())) {
                account.setBalance(account.getBalance().add(amount));
                return true;
            }
        }
        throw new Exception("account with " + playerId + " does not  exists");
    }

    //withdraw from account
    public Boolean Withdraw(String playerName, BigDecimal amount) throws Exception {
        System.out.println("awa 8");
        for (Account account : accountList) {
            if (playerName.equals(account.getName())) {
                if (amount.compareTo(account.getBalance()) <= 0) {
                    account.setBalance(account.getBalance().subtract(amount));
                    System.out.println("awa 9-1");
                    return true;
                } else {
                    System.out.println("awa 9-2");
                    return false;
                }
            }
        }
        throw new Exception("account with " + playerName + " does not  exists");
    }

    //deposit to an account
    public Boolean Deposit(String name, BigDecimal amount) throws Exception {
        for (Account account : accountList) {
            if (name.equals(account.getName())) {
                account.setBalance(account.getBalance().add(amount));
                return true;
            }
        }
        throw new Exception("account with " + name + " does not  exists");
    }

    //return balance for the user
    public BigDecimal Balance(String name) throws Exception {
        for (Account account : accountList) {
            if (name.equals(account.getName())) {
                return account.getBalance();
            }
        }
        throw new Exception("account with " + name + " does not  exists");
    }

}
