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
        stocks.add(new Stock("Apple",Sector.Technology,new BigDecimal(50)));
        stocks.add(new Stock("Microsoft",Sector.Technology,new BigDecimal(44.)));
        stocks.add(new Stock("Google",Sector.Technology,new BigDecimal(52.)));

        stocks.add(new Stock("Allianz",Sector.Finance,new BigDecimal(39.)));
        stocks.add(new Stock("ICBC",Sector.Finance,new BigDecimal(45)));
        stocks.add(new Stock("AXA",Sector.Finance,new BigDecimal(55)));

        stocks.add(new Stock("SERV",Sector.ConsumerServices,new BigDecimal(33.5)));
        stocks.add(new Stock("BID",Sector.ConsumerServices,new BigDecimal(36.5)));
        stocks.add(new Stock("HRB",Sector.ConsumerServices,new BigDecimal(38)));

        stocks.add(new Stock("Volkswagen",Sector.Manufacturing,new BigDecimal(55)));
        stocks.add(new Stock("Samsung",Sector.Manufacturing,new BigDecimal(56)));
        stocks.add(new Stock("Daimler",Sector.Manufacturing,new BigDecimal(34)));

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
            Sector sec=temp.get(i).getSector();
//            need to complet
            temp.add(new Stock(sName+"Temp",sec,stockprice.add(BigDecimal.valueOf(eventValue+sectorValue+marketValue))));

        }
        return temp;
    }


    //change stock value based on events
    public int ChangeEventStockValue(Stock stock,int turn){
        int eventValue=0;
        //loop all events
        for(int i=0;i<eventList.size();i++) {
            if ((eventList.get(i).getSector() == stock.getSector())||(eventList.get(i).getStock()==stock)) {
                if (turn >= eventList.get(i).getStartTurn() && turn <= eventList.get(i).getEndTurn()) {
                    eventValue=eventValue+eventList.get(i).getValue();
                }

            }
        }

        return eventValue;
    }
}
