package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Market {

    public ArrayList<Stock> stocks;
    public HashMap<Sector, ArrayList<Integer>> sectorTrends;
    public ArrayList<Integer> marketTrends;
    public ArrayList<Event> eventList;
    public int turns=20;
    private int currentTurn=0;

    public Market(int turns) {
        this.turns=turns;
        stocks=new ArrayList<>();
        eventList=new ArrayList<>();
        marketTrends= new ArrayList<>();
        sectorTrends=new HashMap<>();
        //add stocks
        stocks.add(new Stock(1l, 100.00, "Google", 1l));
        stocks.add(new Stock(5l, 200.00, "Mail", 2l));
        stocks.add(new Stock(9l, 300.00, "Google", 3l));
        stocks.add(new Stock(10l, 310.00, "Mail", 3l));
        stocks.add(new Stock(11l, 320.00, "BMW", 3l));
        stocks.add(new Stock(12l, 330.00, "Apple", 3l));
        stocks.add(new Stock(13l, 400.00, "Google", 4l));
        stocks.add(new Stock(14l, 410.00, "Mail", 4l));
        stocks.add(new Stock(15l, 420.00, "BMW", 4l));
        stocks.add(new Stock(16l, 430.00, "Apple", 4l));
    }

}
