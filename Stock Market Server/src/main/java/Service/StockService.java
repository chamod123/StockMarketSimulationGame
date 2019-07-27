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

    public static Optional<Stock> getStockBySector(Long Sector) {
        return stocks.stream()
                .filter(stock -> stock.getSector()
                        .equals(Sector))
                .findFirst();
    }

    public static void createStock(Stock user) {
        stocks.add(user);
    }
}
