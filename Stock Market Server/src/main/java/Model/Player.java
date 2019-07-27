package Model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Player {
    private Long id = null;
    private String name;
    HashMap<String,Integer> stocks;

    public Player(String name) {
        this.name = name;
       if(id==null){
           id=1l;
       }else{
           id+=1;
       }
    }

    public Player() {
        this.name = "";
        this.id = null;
    }

    public Player(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public HashMap GetPortofolio() {
        return stocks;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

