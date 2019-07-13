package Model;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private String name;

    //initialize account
    public Account(String playerName) {
        this.name=playerName;
        this.balance=BigDecimal.valueOf(1000);

    }

    public String getName(){
        return this.name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal value) {
        this.balance=value;
    }

}