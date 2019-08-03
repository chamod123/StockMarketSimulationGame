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
    public static int turns = 40;
    public static HashMap<Sector, ArrayList<Integer>> sectorTrends = new HashMap<>();
    public static ArrayList<Integer> marketTrends = new ArrayList<>();
    public static ArrayList<Event> eventList = new ArrayList<>();
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
        return StockService.stocks;
    }

/*
    //get all stocks from csv  for graph
    public static ArrayList<String> getStocksPerGraph() throws Exception {
      */
/*  //Build reader instance
        CSVReader reader = new CSVReader(new FileReader("data.csv"), ',', '"', 0);

        //Read all rows at once
        List<String[]> allRows = reader.readAll();
        // Print Data.
        for (String[] row : allRows) {
            for (String cell : row) {
                System.out.println(cell + "\t");
                String column= cell.substring(0,1);
                System.out.println("1st column " +column);

            }
            System.out.println();
        }*//*


        List<String[]> allData = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            List<String[]>  Data = null;
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader("data.csv");

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            allData = csvReader.readAll();
            List<String> turn = new ArrayList<>();
            List<String> stockValue = new ArrayList<>();
            List<String> stockname = new ArrayList<>();
            // print Data
            for (String[] row : allData) {

                System.out.println("Turn  " + row[0]);
                System.out.println("Stock value :" + row[1] );
                System.out.println("Stock value :" + row[2] );

                turn.add(row[0]);
                stockValue.add(row[1]);
                stockname.add(row[2]);

               */
/* for (String cell : row) {
                    System.out.print(cell + "\t");
                }*//*

                System.out.println();
            }
            System.out.println(turn);
            System.out.println(stockValue);
            System.out.println(stockname);

      */
/*      // declaration and initialise String Array
            String str[] = new String[turn.size()];
            String str2[] = new String[stockValue.size()];

            // ArrayList to Array Conversion
            for (int j = 0; j < turn.size(); j++) {

                // Assign each value to String array
                str[j] = turn.get(j);
                str2[j] = stockValue.get(j);
            }*//*






        */
/*    String[] results1 = new String[turn.size()];
            turn.toArray(results1);
            System.out.println("Array results 1" + Arrays.toString(results1));
            String[] results2 = new String[stockValue.size()];
            stockValue.toArray(results2);
            String[][] results3 = {results1,results2};
            System.out.println(Arrays.toString(results3));
            List<String> list = new ArrayList<String>();
            for (String[] array : results3) {
                list.add("[");
                list.addAll(Arrays.asList(array));
                list.add("]");
            }

            System.out.println("Print list :" + list );*//*

           */
/* List<String> wordList = (List<String>) Arrays.asList(results1,results2);
            System.out.println(wordList);*//*

           */
/* for (int counter = 0; counter <= results1.length; counter++) {
                String temp1=results1[counter];
                String temp2= results2[counter];

                List<String> data = Arrays.asList(new String[]{temp1, temp2});
                List<String> wordList = Arrays.asList(temp1,temp2);
                System.out.println(wordList);
            }*//*

            String[] results1 = new String[turn.size()];
            turn.toArray(results1);
            String x=Arrays.toString(results1);
            String[] results2 = new String[stockValue.size()];
            stockValue.toArray(results2);
            String y=Arrays.toString(results2);
            String[] results3 = new String[stockname.size()];
            stockname.toArray(results3);
            String z=Arrays.toString(results3);



          //  ArrayList<String> list = new ArrayList<String>();
            list.add(x);
            list.add(y);
            list.add(z);

            System.out.println("Latest : " + list);


            // al = new String[][]{str, str2};
            // System.out.println(al);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;


        // List<String> data ={turn};

        //return allData;
    }
*/
//get all stocks from csv  for graph
public static List<String[]> getStocksPerGraph() throws Exception {

    //Build reader instance
    CSVReader reader = new CSVReader(new FileReader("data.csv"), ',', '"', 0);

    //Read all rows at once
    List<String[]> allRows = reader.readAll();

    allRows.remove(2);
    return allRows;
}

    public static List<String[]> getGraphpername(String companyname) throws Exception {

        //Build reader instance
        CSVReader reader = new CSVReader(new FileReader("data.csv"), ',', '"', 0);

        //Read all rows at once
        List<String[]> allRows = reader.readAll();


        List<String[]> selectedRows = new ArrayList<>();
        List<String> turn = new ArrayList<>();
        List<String> stockValue = new ArrayList<>();

/*        for (Account account : accountList) {
            if (name.equals(account.getName())) {
                return account.getBalance();
            }
        }*/
       // ArrayList<String> list = null;
        for (String[] row : allRows) {
            if (companyname.equals(row[2])) {

                String[] x= {String.valueOf(row[0]),String.valueOf(row[1])};
           //     String[] y= {String.valueOf(row[1])};
                selectedRows.add(x);
             //   selectedRows.add(y);
            }
            //String[] results1 = new String[turn.size()];
            //turn.toArray(results1);
            //String x = Arrays.toString(results1);
            //String[] results2 = new String[stockValue.size()];
            //stockValue.toArray(results2);
            //String y = Arrays.toString(results2);


            //list = new ArrayList<String>();
            //list.add(x);
            //list.add(y);

         //   System.out.println("Latest : " + list);


            //selectedRows.add(turns);

        }


        return selectedRows;
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
        ArrayList<Stock> temp = new ArrayList<>();
        ArrayList<Stock> temp2 = new ArrayList<>();
//                temp = StockService.stocks;
            for (Stock stock : StockService.stocks) {
                temp.add(stock);
            }

        Random ran = new Random();

        int number = ran.nextInt(3) + currentTurn;
//        System.out.println("number " + number);

        int size = StockService.stocks.size();

        for (int i = 0; i < size; i++) {

            //get market trend value for current turn
            int marketValue = marketTrends.get(number);
//            System.out.println("marketValue " + marketValue);

            //get sector trend value value for current turn
            int sectorValue = sectorTrends.get(temp.get(i).getSector()).get(number);
            int eventValue = ChangeEventStockValue(temp.get(i), number);
            String sName = temp.get(i).getCompanyName();
            BigDecimal stockprice = temp.get(i).getStockPrice();
            Sector sec = temp.get(i).getSector();
//            need to complet
            temp2.add(new Stock(sName , sec, stockprice.add(BigDecimal.valueOf(eventValue + sectorValue + marketValue))));

        }
//        System.out.println("temp " + temp2);
        return temp2;
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
        for (int i = 0; i < StockService.stocks.size(); i++) {

            String csv = "data.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            String turnString = String.valueOf(GetCurrentTurn());
            String record = StockService.stocks.get(i).getStockPrice().toString();
            String name = StockService.stocks.get(i).getCompanyName().toString();
            String[] record3 = {turnString,record,name};
            writer.writeNext(record3);
            writer.close();

        }
        return GetCurrentTurn();
    }


    //change stock value based on event market and sector trends
    public static void ChangeStockValues() throws IOException {

        for (int i = 0; i < StockService.stocks.size(); i++) {
            //get market trend value for current turn

            int marketValue = marketTrends.get(currentTurn);
            //get sector trend value value for current turn
            int sectorValue = sectorTrends.get(StockService.stocks.get(i).getSector()).get(currentTurn);
            int eventValue = ChangeEventStockValue(StockService.stocks.get(i), currentTurn);
            BigDecimal stockprice = StockService.stocks.get(i).getStockPrice();
            StockService.stocks.get(i).setStockPrice(stockprice.add(BigDecimal.valueOf(eventValue + sectorValue + marketValue)));
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
            if (currentTurn >= eventList.get(i).getStartTurn() && currentTurn <= eventList.get(i).getEndTurn()) {
                eList.add(eventList.get(i));
            }
        }
        return eList;
    }

    public static void setTrends() {
        //set market Trends

        int nextInt;
        for (int i = 0; i < turns; i++) {
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


        //set sector trends
        for (Sector d : Sector.values()) {
            ArrayList<Integer> trends = new ArrayList();
            for (int i = 0; i < turns; i++) {
                nextInt = random.nextInt(100);
                if (nextInt < 50) {
                    if (nextInt > 25) {
                        trends.add(randomvalue.nextInt(4) - 5);
                    } else {
                        trends.add(randomvalue.nextInt(4) + 1);
                    }
                } else {
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
