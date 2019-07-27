package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Market {
    public int turns=20;
    String username;
    String stock;
    int quantity;

    private int currentTurn=0;

    public Market(int turns) {
        this.turns=turns;
    }
         public Market() {
        this.username = "";
        this.stock = "";
    }

    public Market(String username, String stock, int quantity) {
        this.username = username;
        this.stock = stock;
        this.quantity=quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

}
