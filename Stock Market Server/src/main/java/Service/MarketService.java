package Service;

import Model.Event;
import Model.Sector;
import Model.Stock;

import javax.management.AttributeList;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class MarketService {
    public static ArrayList<Stock> stocks;
    public HashMap<Sector, ArrayList<Integer>> sectorTrends;
    public ArrayList<Integer> marketTrends;
    public ArrayList<Event> eventList;
    private int currentTurn=0;



    static {
        stocks=new ArrayList<>();
        stocks.add(new Stock(1l, new BigDecimal(10.00), "Google", 1l));
        stocks.add(new Stock(5l, new BigDecimal(20.00), "Mail", 2l));
        stocks.add(new Stock(9l, new BigDecimal(30.00), "Google", 3l));
        stocks.add(new Stock(10l, new BigDecimal(31.00), "Mail", 3l));
        stocks.add(new Stock(11l, new BigDecimal(32.00), "BMW", 3l));
        stocks.add(new Stock(12l, new BigDecimal(33.00), "Apple", 3l));
        stocks.add(new Stock(13l, new BigDecimal(40.00), "Google", 4l));
        stocks.add(new Stock(14l, new BigDecimal(41.00), "Mail", 4l));
        stocks.add(new Stock(15l, new BigDecimal(42.00), "BMW", 4l));
        stocks.add(new Stock(16l, new BigDecimal(43.00), "Apple", 4l));}

    //get stock buy name
    public Stock getStock(String name) throws Exception {
        for (Stock c : stocks) {
            if (name.equals(c.getCompanyName())) {
                return c;
            }
        }
        throw new Exception("Stock with the name "+name+" does not exsist");
    }

    //get current turn
    public int GetCurrentTurn() {
        return this.currentTurn;
    }


}
