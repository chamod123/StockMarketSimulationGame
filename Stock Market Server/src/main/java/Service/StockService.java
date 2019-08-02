package Service;

import Model.Sector;
import Model.Stock;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {
    public static ArrayList<Stock> stocks = new ArrayList<>();
    public static MarketService marketService=new MarketService();

    static {
        stocks.add(new Stock("Apple", Sector.Technology, BigDecimal.valueOf(11.00)));
        stocks.add(new Stock("BMW", Sector.Manufacturing, BigDecimal.valueOf(21.00)));
        stocks.add(new Stock("eBay", Sector.Manufacturing, BigDecimal.valueOf(32.00)));
        stocks.add(new Stock("AWS", Sector.ConsumerServices, BigDecimal.valueOf(65.00)));
        stocks.add(new Stock("MicroSoft", Sector.Finance, BigDecimal.valueOf(78.00)));
        stocks.add(new Stock("Vine", Sector.Finance, BigDecimal.valueOf(10.00)));
        stocks.add(new Stock("Oppo", Sector.Manufacturing, BigDecimal.valueOf(7.00)));
        stocks.add(new Stock("Titan", Sector.Technology, BigDecimal.valueOf(10.00)));
        stocks.add(new Stock("AXE", Sector.ConsumerServices, BigDecimal.valueOf(25.00)));
        stocks.add(new Stock("Tesla", Sector.Technology, BigDecimal.valueOf(13.00)));
        for (int i = 0; i < stocks.size(); i++) {

            String csv = "data.csv";
            CSVWriter writer = null;
            try {
                writer = new CSVWriter(new FileWriter(csv, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String turnString = String.valueOf(marketService.GetCurrentTurn());
            String record = StockService.stocks.get(i).getStockPrice().toString();
            String[] record3 = {turnString,record};
            writer.writeNext(record3);
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static Optional<Stock> getStock(Long id) {
        return stocks.stream()
                .filter(stock -> Long.valueOf(stock.getStockId())
                        .equals(id))
                .findFirst();
    }

    public static List<Stock> getStockBySector(String Sector) {
        List<Stock> stocks2 = new ArrayList<>();
        for (Stock stock1 : stocks) {
            if (stock1.getSector().toString().equals(Sector)) {
                stocks2.add(stock1);
            }
        }
        return stocks2;
//        return stocks.stream()
//                .filter(stock -> stock.getSector().toString()
//                        .equals(Sector))
//                .findAny();
    }

    public static void createStock(Stock stock) throws IOException {
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        stock.setStockId(stocks.size() + 1);
        stocks.add(stock);
        String turnString = String.valueOf(marketService.GetCurrentTurn());
        String record = stock.getStockPrice().toString();
        String[] record3 = {record};
        writer.writeNext(record3);
        writer.close();
//        for (Stock stock1 : stocks) {
//            System.out.println("accountList  " + stock1.getCompanyName() +"  " + stock1.getStockId()+"  " + stock1.getSector());
//        }
    }

    public static void updateStock(Stock stock) {
        for (Stock stock1 : stocks) {
            if (stock.getStockId() == stock1.getStockId()) {
                stock1.setStockPrice(stock.getStockPrice());
                stock1.setSector(stock.getSector());
            }
        }
    }
}
