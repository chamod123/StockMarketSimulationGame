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

    public Optional<Stock> getStockBySector(Long Sector) {
        return stocks.stream()
                .filter(stock -> stock.getSector()
                        .equals(Sector))
                .findFirst();
    }

    public void createStock(Stock stock) {
        stock.setStockId(stocks.size()+1);
        stocks.add(stock);
        for (Stock stock1 : stocks) {
            System.out.println("accountList  " + stock1.getCompanyName() +"  " + stock1.getStockId());
        }
    }
}
