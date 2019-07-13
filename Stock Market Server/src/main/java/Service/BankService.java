package Service;

import Model.Account;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final static List<Account> accountList = new ArrayList<>();

    public void CreateAccount(Account Account1)  {
            accountList.add(Account1);

//        for (Account account : accountList) {
//            if (Account1.getName().equals(account.getName())) {
//                System.out.println("Account Already Have");
//            } else {
//                accountList.add(Account1);
//                System.out.println("Account Added");
//            }
//        }
    }
}
