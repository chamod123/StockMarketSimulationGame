package Service;

import Model.Event;
import Model.Sector;
import Model.Stock;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
/*import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.network.protocol.Encoders;
import org.apache.spark.*;
import org.apache.spark.sql.*;
import org.apache.spark.sql.Dataset;*/

import javax.management.AttributeList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static java.lang.String.valueOf;
//import java.io.FileWriter;
//import au.com.bytecode.opencsv.CSVWriter;

public class MarketService {
    public static ArrayList<Stock> stocks;
    public static ArrayList<Stock> stocksHistory;
    public int turns = 20;
    public static HashMap<Sector, ArrayList<Integer>> sectorTrends;
    public static ArrayList<Integer> marketTrends;
    public static ArrayList<Event> eventList;
    private int currentTurn = 0;
    Random random = new Random();
    Random randomvalue = new Random();
    public static String csv = "data.csv";

    public static CSVWriter writer;

    static {
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stocks = new ArrayList<>();
        eventList = new ArrayList<>();
        marketTrends = new ArrayList<>();
        sectorTrends = new HashMap<>();
        //add stocks
        stocks.add(new Stock("Apple", Sector.Technology, new BigDecimal(50)));
        stocks.add(new Stock("Microsoft", Sector.Technology, new BigDecimal(44.)));
        stocks.add(new Stock("Google", Sector.Technology, new BigDecimal(52.)));

        stocks.add(new Stock("Allianz", Sector.Finance, new BigDecimal(39.)));
        stocks.add(new Stock("ICBC", Sector.Finance, new BigDecimal(45)));
        stocks.add(new Stock("AXA", Sector.Finance, new BigDecimal(55)));

        stocks.add(new Stock("SERV", Sector.ConsumerServices, new BigDecimal(33.5)));
        stocks.add(new Stock("BID", Sector.ConsumerServices, new BigDecimal(36.5)));
        stocks.add(new Stock("HRB", Sector.ConsumerServices, new BigDecimal(38)));

        stocks.add(new Stock("Volkswagen", Sector.Manufacturing, new BigDecimal(55)));
        stocks.add(new Stock("Samsung", Sector.Manufacturing, new BigDecimal(56)));
        stocks.add(new Stock("Daimler", Sector.Manufacturing, new BigDecimal(34)));

        //will remove after the front end integration

        for (int i = 0; i < stocks.size(); i++) {
            String record = stocks.get(i).getStockPrice().toString();
            String [] record3 = {record};
            writer.writeNext(record3);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public MarketService() {
        setEvents();
        setTrends();
        ChangeStockValues();
    }


    //get stock buy name
    public Stock getStock(String name) throws Exception {
        for (Stock c : stocks) {
            if (name.equals(c.getCompanyName())) {

                return c;
            }
        }
        throw new Exception("Stock with the name " + name + " does not exsist");

    }

    //get all stocks
    public ArrayList<Stock> getStocks() throws Exception {
        return this.stocks;
    }

    //get all stocks from csv  for graph
    public ArrayList<String> getStocksPerGraph() throws Exception {
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader("data.csv"), ',', '"', 0);

        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        return (ArrayList<String>) allRows;
    }

/*
    //get all stocks for charts
    public Dataset<String> getStocksCharts() throws Exception {
        return dataDs;
    }
*/


    //get current turn
    public int GetCurrentTurn() {
        return this.currentTurn;
    }

    //return future array list
    public ArrayList<Stock> GetPredictedStocks() {
        ArrayList<Stock> temp = new ArrayList<Stock>(stocks);
        Random ran = new Random();
        int number = ran.nextInt(3) + currentTurn;
        for (int i = 0; i < stocks.size(); i++) {
            //get market trend value for current turn
            int marketValue = marketTrends.get(number);
            //get sector trend value value for current turn
            int sectorValue = sectorTrends.get(temp.get(i).getSector()).get(number);
            int eventValue = ChangeEventStockValue(temp.get(i), number);
            String sName = temp.get(i).getCompanyName();
            BigDecimal stockprice = temp.get(i).getStockPrice();
            Sector sec = temp.get(i).getSector();
//            need to complet
            temp.add(new Stock(sName + "Temp", sec, stockprice.add(BigDecimal.valueOf(eventValue + sectorValue + marketValue))));

        }
        return temp;
    }


    //change stock value based on events
    public int ChangeEventStockValue(Stock stock, int turn) {
        int eventValue = 0;
        //loop all events
        for (int i = 0; i < eventList.size(); i++) {
            if ((eventList.get(i).getSector() == stock.getSector()) || (eventList.get(i).getStock() == stock)) {
                if (turn >= eventList.get(i).getStartTurn() && turn <= eventList.get(i).getEndTurn()) {
                    eventValue = eventValue + eventList.get(i).getValue();
                }

            }
        }

        return eventValue;
    }


    //go to next turn
    public int NextTurn() {
        this.currentTurn++;
        ChangeStockValues();
        return GetCurrentTurn();
    }


    //change stock value based on event market and sector trends
    public void ChangeStockValues() {

        for (int i = 0; i < stocks.size(); i++) {
            //get market trend value for current turn

            int marketValue = marketTrends.get(currentTurn);
            //get sector trend value value for current turn
            int sectorValue = sectorTrends.get(stocks.get(i).getSector()).get(currentTurn);
            int eventValue = ChangeEventStockValue(stocks.get(i), currentTurn);
            BigDecimal stockprice = stocks.get(i).getStockPrice();
            stocks.get(i).setStockPrice(stockprice.add(BigDecimal.valueOf(eventValue + sectorValue + marketValue)));
            if (stocks.get(i).getStockPrice().compareTo(BigDecimal.ZERO) <= 0) {
                stocks.get(i).setStockPrice(BigDecimal.valueOf(1));
            }
        }

        for (int i = 0; i < stocks.size(); i++) {
            String record = stocks.get(i).getStockPrice().toString();
            String [] record3 = {record};
            writer.writeNext(record3);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //get current events
    public ArrayList<Event> GetCurrentEvents() {
        ArrayList<Event> eList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            if (this.currentTurn >= eventList.get(i).getStartTurn() && this.currentTurn <= eventList.get(i).getEndTurn()) {
                eList.add(eventList.get(i));
            }
        }
        return eList;
    }

    public void setTrends() {
        //set market Trends

        int nextInt;
        for (int i = 0; i < this.turns; i++) {
            nextInt = random.nextInt(100);
            if (nextInt < 50) {
                if (nextInt > 25) {
                    marketTrends.add(randomvalue.nextInt(4) - 5);
                    ;
                } else {
                    marketTrends.add(randomvalue.nextInt(4) + 1);
                }

            } else {
                marketTrends.add(0);
            }

        }
    }

    public void setEvents() {
        int probability = 0;
        Random random = new Random();
        int nextInt;
        //loop for number of turns in the game
        for (int i = 0; i < this.turns; i++) {
            nextInt = random.nextInt(10);
            if (nextInt < probability) {
                Event evnt = new Event(stocks, i);
                eventList.add(evnt);
                probability = 0;
            }
            probability++;

        }
    }
}
