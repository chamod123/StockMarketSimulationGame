package Model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Player {
    private int id;
    private String name;
    HashMap<String,Integer> stocks;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        this.name = "";
//        this.id = null;
    }

    public Player(int id, String name) {
        this.name = name;
        this.id = id;
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
}

