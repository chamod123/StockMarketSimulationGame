package Service;

import Model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final static List<Account> accountList = new ArrayList<>();

    public void CreateAccount(Account Account1)  {
        System.out.println("Account added " + Account1.getPlayerId());
            accountList.add(Account1);
    }

    //deposit to an account
    public Boolean Deposit(int turn,Long playerId,String sender,BigDecimal amount) throws Exception {
        for (Account account : accountList) {
            if (playerId.equals(account.getPlayerId())) {
                account.setBalance(account.getBalance().add(amount));
                return true;
            }
        }
        throw new Exception("account with "+playerId+" does not  exists");
    }

    //withdraw from account
    public Boolean Withdraw(int turn,Long playerId,String receiver,BigDecimal amount) throws Exception {
        for (Account account : accountList) {
            if (playerId.equals(account.getPlayerId())) {
                if (amount.compareTo(account.getBalance())<=0) {
                    account.setBalance(account.getBalance().subtract(amount));
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        throw new Exception("account with "+playerId+" does not  exists");
    }

}
