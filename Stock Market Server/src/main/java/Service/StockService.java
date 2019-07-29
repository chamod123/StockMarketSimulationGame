package Service;

import Model.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {
    private final static List<Stock> stocks = new ArrayList<>();

    public static Optional<Stock> getStock(Long id) {
        return stocks.stream()
                .filter(stock -> Long.valueOf(stock.getStockId())
                        .equals(id))
                .findFirst();
    }

    public List<Stock> getStockBySector(String Sector) {
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

    public void createStock(Stock stock) {
        stock.setStockId(stocks.size()+1);
        stocks.add(stock);
        for (Stock stock1 : stocks) {
            System.out.println("accountList  " + stock1.getCompanyName() +"  " + stock1.getStockId()+"  " + stock1.getSector());
        }
    }
}
