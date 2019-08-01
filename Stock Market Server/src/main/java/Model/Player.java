package Model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Player {
    private int id;
    private String name;
    private String secondName;
    private String email;
    private String userName;
    private String password;
    private String tpNumber;
    HashMap<String, Integer> stocks = new HashMap<>();

    public Player(String name, String secondName, String email, String userName, String password, String tpNumber) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.tpNumber = tpNumber;
    }

    public Player(String userName,String password) {
        this.userName=userName;
        this.password=password;
    }

    public Player() {
    }

    public Player(String name) {
        this.name = name;
//        this.id = null;
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
//        this.id = null;
    }

    public Player(int id, String name, String secondName, String email, String userName, String password, String tpNumber) {
        this.name = name;
        this.id = id;
        this.secondName = secondName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.tpNumber = tpNumber;
    }

    public HashMap GetPortofolio() {
        return stocks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getStocks() {
        return stocks;
    }

    public void setStocks(HashMap<String, Integer> stocks) {
        this.stocks = stocks;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(String tpNumber) {
        this.tpNumber = tpNumber;
    }
}

