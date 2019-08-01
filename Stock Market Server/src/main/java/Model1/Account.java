package Old.Model1;

import java.math.BigDecimal;

public class Account {

    private String name;
    private Long accountId = 0l;
    private BigDecimal balance;
    private Long playerId;

    //initialize account
    public Account(String name) {
        this.name = name;
        this.accountId += 1;
        this.balance = BigDecimal.valueOf(1000);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayerId() {
        return this.playerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}