package Service;

import Model.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {
    private final static List<Stock> stocks = new ArrayList<>();

    static {
        stocks.add(new Stock(1l,100.00,"Google"));
        stocks.add(new Stock(2l,200.00,"Mail"));
        stocks.add(new Stock(3l,300.00,"BMW"));
        stocks.add(new Stock(4l,400.00,"Apple"));
    }

    public static Optional<Stock> getStock(Long id) {
        return stocks.stream()
                .filter(stock -> stock.getStockId()
                        .equals(id))
                .findFirst();
    }
    public static void createStock(Stock user) {
        stocks.add(user);
    }
}
