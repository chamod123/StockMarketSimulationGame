package Service;

import Model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final static List<Account> accountList = new ArrayList<>();

    public static void CreateAccount(Account Account1) {
        accountList.add(Account1);
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

    //checked
    //withdraw from account
    public static Boolean Withdraw(String playerName, BigDecimal amount) throws Exception {
        for (Account account : accountList) {
            if (playerName.equals(account.getName())) {
                System.out.println("playerName: " + playerName + " before balance: " + account.getBalance());
                if (amount.compareTo(account.getBalance()) <= 0) {
                    account.setBalance(account.getBalance().subtract(amount));
                    System.out.println("playerName: " + playerName + " balance: " + account.getBalance());
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new Exception("account with " + playerName + " does not  exists");
    }

    //deposit to an account
    public static Boolean Deposit(String playerName, BigDecimal amount) throws Exception {
        for (Account account : accountList) {
            if (playerName.equals(account.getName())) {
                account.setBalance(account.getBalance().add(amount));
                System.out.println("playerName: " + playerName + " balance: " + account.getBalance());
                return true;
            }
        }
        throw new Exception("account with " + playerName + " does not  exists");
    }

    //return balance for the user
    public static BigDecimal Balance(String name) throws Exception {
        for (Account account : accountList) {
            if (name.equals(account.getName())) {
                return account.getBalance();
            }
        }
        throw new Exception("account with " + name + " does not  exists");
    }

}
