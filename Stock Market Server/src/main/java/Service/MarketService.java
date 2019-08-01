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
//    public static ArrayList<Stock> stocks; // get stock from StockService
    public static ArrayList<Stock> stocksHistory;
    public static int turns = 20;
    public static HashMap<Sector, ArrayList<Integer>> sectorTrends = new HashMap<>();
    public static ArrayList<Integer> marketTrends= new ArrayList<>();
    public static ArrayList<Event> eventList= new ArrayList<>();
    private static int currentTurn = 1;
    static Random random = new Random();
    static Random randomvalue = new Random();
    public static String csv = "data.csv";

    public static CSVWriter writer;

    static {
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        StockService.stocks = new ArrayList<>();
//        eventList = new ArrayList<>();
//        marketTrends = new ArrayList<>();
//        sectorTrends = new HashMap<>();
        //add stocks
//        stocks.add(new Stock("Apple", Sector.Technology, new BigDecimal(50)));
//        stocks.add(new Stock("Microsoft", Sector.Technology, new BigDecimal(44.)));
//        stocks.add(new Stock("Google", Sector.Technology, new BigDecimal(52.)));
//
//        stocks.add(new Stock("Allianz", Sector.Finance, new BigDecimal(39.)));
//        stocks.add(new Stock("ICBC", Sector.Finance, new BigDecimal(45)));
//        stocks.add(new Stock("AXA", Sector.Finance, new BigDecimal(55)));
//
//        stocks.add(new Stock("SERV", Sector.ConsumerServices, new BigDecimal(33.5)));
//        stocks.add(new Stock("BID", Sector.ConsumerServices, new BigDecimal(36.5)));
//        stocks.add(new Stock("HRB", Sector.ConsumerServices, new BigDecimal(38)));
//
//        stocks.add(new Stock("Volkswagen", Sector.Manufacturing, new BigDecimal(55)));
//        stocks.add(new Stock("Samsung", Sector.Manufacturing, new BigDecimal(56)));
//        stocks.add(new Stock("Daimler", Sector.Manufacturing, new BigDecimal(34)));

        //will remove after the front end integration

/*        for (int i = 0; i < StockService.stocks.size(); i++) {
            String record = StockService.stocks.get(i).getStockPrice().toString();
            String [] record3 = {record};
            writer.writeNext(record3);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


//        setEvents();
//        setTrends();
//        ChangeStockValues();
    }


    //get stock buy name
    public static Stock getStock(String name) throws Exception {
        for (Stock c : StockService.stocks) {
            if (name.equals(c.getCompanyName())) {

                return c;
            }
        }
        throw new Exception("Stock with the name " + name + " does not exsist");

    }

    //get all stocks
    public static ArrayList<Stock> getStocks() throws Exception {
        for (Stock stock1 : StockService.stocks) {
          System.out.println("all stock "+stock1.getCompanyName());
        }
        return StockService.stocks;
    }

    //get all stocks from csv  for graph
    public static List<String[]> getStocksPerGraph() throws Exception {
        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader("data.csv"), ',', '"', 0);

        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        return allRows;
    }

/*
    //get all stocks for charts
    public Dataset<String> getStocksCharts() throws Exception {
        return dataDs;
    }
*/


    //get current turn
    public static int GetCurrentTurn() {
        return currentTurn;
    }

    //return future array list
    public static ArrayList<Stock> GetPredictedStocks() {
        ArrayList<Stock> temp = StockService.stocks;
        Random ran = new Random();
        int number = ran.nextInt(3) + currentTurn;
        for (int i = 0; i < StockService.stocks.size(); i++) {
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
    public static int ChangeEventStockValue(Stock stock, int turn) {
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
    public static int NextTurn() throws IOException {
        currentTurn++;
        ChangeStockValues();
        return GetCurrentTurn();
    }


    //change stock value based on event market and sector trends
    public static void ChangeStockValues() throws IOException {

        for (int i = 0; i < StockService.stocks.size(); i++) {
            //get market trend value for current turn

            int marketValue = marketTrends.get(currentTurn);
            //get sector trend value value for current turn
            System.out.println("awa company " + StockService.stocks.get(i).getCompanyName());
            System.out.println("awa sector " + StockService.stocks.get(i).getCompanyName());
            System.out.println("awa sector " + sectorTrends.get(StockService.stocks.get(i).getSector()).get(currentTurn));
            int sectorValue = sectorTrends.get(StockService.stocks.get(i).getSector()).get(currentTurn);
            int eventValue = ChangeEventStockValue(StockService.stocks.get(i), currentTurn);
            BigDecimal stockprice = StockService.stocks.get(i).getStockPrice();
            StockService.stocks.get(i).setStockPrice(stockprice.add(BigDecimal.valueOf(eventValue + sectorValue + marketValue)));
            String csv = "data.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));

            String record = StockService.stocks.get(i).getStockPrice().toString();
            String [] record3 = {record};
            writer.writeNext(record3);

            writer.close();

            if (StockService.stocks.get(i).getStockPrice().compareTo(BigDecimal.ZERO) <= 0) {
                StockService.stocks.get(i).setStockPrice(BigDecimal.valueOf(1));
            }
        }

    /*    for (int i = 0; i < StockService.stocks.size(); i++) {
            String record = StockService.stocks.get(i).getStockPrice().toString();
            String [] record3 = {record};
            writer.writeNext(record3);
        }*/
//        try {
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    //get current events
    public static ArrayList<Event> GetCurrentEvents() {
        ArrayList<Event> eList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            System.out.println("eventList end: " + eventList.get(i).getEndTurn() + " Start :" + eventList.get(i).getStartTurn());
            if (currentTurn >= eventList.get(i).getStartTurn() && currentTurn <= eventList.get(i).getEndTurn()) {
                eList.add(eventList.get(i));
            }
        }
        return eList;
    }

    public static void setTrends() {
        //set market Trends

        int nextInt;
        for(int i=0;i<turns;i++) {
            nextInt = random.nextInt(100);
            if (nextInt < 50) {
                if (nextInt > 25) {
                    marketTrends.add(randomvalue.nextInt(4) - 5);;
                }else {
                    marketTrends.add(randomvalue.nextInt(4)+1);
                }

            }else {
                marketTrends.add(0);
            }

        }


        //set sector trends
        for (Sector d : Sector.values()) {
            ArrayList<Integer> trends=new ArrayList();
            for(int i=0;i<turns;i++) {
                nextInt = random.nextInt(100);
                if (nextInt < 50) {
                    if (nextInt > 25) {
                        trends.add(randomvalue.nextInt(4) - 5);
                    }else {
                        trends.add(randomvalue.nextInt(4)+1);
                    }

                }else {
                    trends.add(0);
                }

            }
            sectorTrends.put(d, trends);


        }

    }

    public static void setEvents() {
        int probability = 0;
        Random random = new Random();
        int nextInt;
        //loop for number of turns in the game
        for (int i = 0; i < turns; i++) {
            nextInt = random.nextInt(10);
            if (nextInt < probability) {
                Event evnt = new Event(StockService.stocks, i);
                eventList.add(evnt);
                probability = 0;
            }
            probability++;

        }
    }
}
