package Model;

import java.math.BigDecimal;

public class Account {

    private String name;
    private Long accountId = 0l;
    private BigDecimal balance;
    private BigDecimal amount;
    private Long playerId;

    //initialize account
    public Account(Long playerId) {
        this.playerId=playerId;
        this.accountId += 1;
        this.balance=BigDecimal.valueOf(1000);
    }

    public Account(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPlayerId(){
        return this.playerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal value) {
        this.balance=value;
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