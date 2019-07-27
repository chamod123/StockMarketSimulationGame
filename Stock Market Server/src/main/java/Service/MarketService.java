package Service;

import Model.Event;
import Model.Sector;
import Model.Stock;

import javax.management.AttributeList;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class MarketService {
    public static ArrayList<Stock> stocks;
    public int turns=20;
    public static HashMap<Sector, ArrayList<Integer>> sectorTrends;
    public static ArrayList<Integer> marketTrends;
    public static ArrayList<Event> eventList;
    private int currentTurn=0;


    static {
        stocks = new ArrayList<>();
        eventList = new ArrayList<>();
        marketTrends = new ArrayList<>();
        sectorTrends = new HashMap<>();
        //add stocks
        stocks.add(new Stock(1l, new BigDecimal(10.00), "Google", 1l));
        stocks.add(new Stock(5l, new BigDecimal(20.00), "Mail", 2l));
        stocks.add(new Stock(9l, new BigDecimal(30.00), "Google", 3l));
        stocks.add(new Stock(10l, new BigDecimal(31.00), "Mail", 3l));
        stocks.add(new Stock(11l, new BigDecimal(32.00), "BMW", 3l));
        stocks.add(new Stock(12l, new BigDecimal(33.00), "Apple", 3l));
        stocks.add(new Stock(13l, new BigDecimal(40.00), "Google", 4l));
        stocks.add(new Stock(14l, new BigDecimal(41.00), "Mail", 4l));
        stocks.add(new Stock(15l, new BigDecimal(42.00), "BMW", 4l));
        stocks.add(new Stock(16l, new BigDecimal(43.00), "Apple", 4l));

    }


    //get stock buy name
    public Stock getStock(String name) throws Exception {
        for (Stock c : stocks) {
            if (name.equals(c.getCompanyName())) {
                return c;
            }
        }
        throw new Exception("Stock with the name "+name+" does not exsist");
    }

    //get all stocks
    public ArrayList<Stock> getStocks() throws Exception {
        return this.stocks;

    }

    //get current turn
    public int GetCurrentTurn() {
        return this.currentTurn;
    }

    //return future array list
    public ArrayList<Stock> GetPredictedStocks() {
        ArrayList<Stock> temp=new ArrayList<Stock>(stocks);
        Random ran = new Random();
        int number = ran.nextInt(3)+currentTurn;
        for (int i = 0; i < stocks.size(); i++) {
            //get market trend value for current turn
            int marketValue=marketTrends.get(number);
            //get sector trend value value for current turn
            int sectorValue=sectorTrends.get(temp.get(i).getSector()).get(number);
            int eventValue=ChangeEventStockValue(temp.get(i),number);
            String sName=temp.get(i).getCompanyName();
            BigDecimal stockprice=temp.get(i).getStockPrice();
            Sector sec=temp.get(i).getSectorObj();
//            need to complet
//            temp.add(new Stock(sName+"Temp",sec,stockprice.add(BigDecimal.valueOf(eventValue+sectorValue+marketValue))));
        }
        return temp;
    }


    //change stock value based on events
    public int ChangeEventStockValue(Stock stock,int turn){
        int eventValue=0;
        //loop all events
        for(int i=0;i<eventList.size();i++) {
            if ((eventList.get(i).getSector() == stock.getSectorObj())||(eventList.get(i).getStock()==stock)) {
                if (turn >= eventList.get(i).getStartTurn() && turn <= eventList.get(i).getEndTurn()) {
                    eventValue=eventValue+eventList.get(i).getValue();
                }

            }
        }

        return eventValue;
    }
}
