package Model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {

    private String name;
    private int accountId;
    private BigDecimal balance;
    private Long playerId;
    private String cvs;
    private String cardNo;
    private String expierDate;


    public Account(String name, String cvs, String cardNo ,String expierDate) {
        this.name = name;
        this.cvs=cvs;
        this.cardNo=cardNo;
        this.expierDate=expierDate;

    }
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpierDate() {
        return expierDate;
    }

    public void setExpierDate(String expierDate) {
        this.expierDate = expierDate;
    }
}