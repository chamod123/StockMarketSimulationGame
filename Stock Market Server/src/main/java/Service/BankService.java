package Service;

import Model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final static List<Account> accountList = new ArrayList<>();

    public static void CreateAccount(Account Account1) {
        accountList.add(Account1);
    }

    public static List<Account> getAccountsDetails(){
        return accountList;
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
                if (amount.compareTo(account.getBalance()) <= 0) {
                    account.setBalance(account.getBalance().subtract(amount));
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

    public static void updateAccount(Account account) {


        for (Account account1 : accountList) {
            if(account.getName().equals(account1.getName())){
                account1.setCardNo(account.getCardNo());
                account1.setCvs(account.getCvs());
                account1.setExpierDate(account.getExpierDate());
            }
        }
    }

}
