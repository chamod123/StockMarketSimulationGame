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
        stocks.add(new Stock(1l, new BigDecimal(100.00), "Google", 1l));
        stocks.add(new Stock(5l, new BigDecimal(200.00), "Mail", 2l));
        stocks.add(new Stock(9l, new BigDecimal(300.00), "Google", 3l));
        stocks.add(new Stock(10l, new BigDecimal(310.00), "Mail", 3l));
        stocks.add(new Stock(11l, new BigDecimal(320.00), "BMW", 3l));
        stocks.add(new Stock(12l, new BigDecimal(330.00), "Apple", 3l));
        stocks.add(new Stock(13l, new BigDecimal(400.00), "Google", 4l));
        stocks.add(new Stock(14l, new BigDecimal(410.00), "Mail", 4l));
        stocks.add(new Stock(15l, new BigDecimal(420.00), "BMW", 4l));
        stocks.add(new Stock(16l, new BigDecimal(430.00), "Apple", 4l));
    }

}
