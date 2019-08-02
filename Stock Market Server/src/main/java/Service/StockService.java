package Service;

import Model.Stock;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {
    public static ArrayList<Stock> stocks = new ArrayList<>();
    public static MarketService marketService=new MarketService();

    public static Optional<Stock> getStock(Long id) {
        return stocks.stream()
                .filter(stock -> Long.valueOf(stock.getStockId())
                        .equals(id))
                .findFirst();
    }

    public static List<Stock> getStockBySector(String Sector) {
        List<Stock> stocks2 = new ArrayList<>();
        for (Stock stock1 : stocks) {
            if(stock1.getSector().toString().equals(Sector)){
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
        stock.setStockId(stocks.size()+1);
        stocks.add(stock);
        String turn=String.valueOf(marketService.GetCurrentTurn());
        String record =stock.getStockPrice().toString();
        String [] record3 = {turn,record};
        writer.writeNext(record3);
        writer.close();
        for (Stock stock1 : stocks) {
            System.out.println("accountList  " + stock1.getCompanyName() +"  " + stock1.getStockId()+"  " + stock1.getSector());

        }
    }
}
