package Service;

import Model.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {
    private final static List<Stock> stocks = new ArrayList<>();
    static {
        stocks.add(new Stock(1l,100.00,"Google","Finance"));
        stocks.add(new Stock(2l,110.00,"Google","Finance"));
        stocks.add(new Stock(3l,120.00,"Google","Finance"));
        stocks.add(new Stock(4l,130.00,"Google","Finance"));
        stocks.add(new Stock(5l,200.00,"Mail","Technology"));
        stocks.add(new Stock(6l,210.00,"Mail","Technology"));
        stocks.add(new Stock(7l,220.00,"Mail","Technology"));
        stocks.add(new Stock(8l,230.00,"Mail","Technology"));
        stocks.add(new Stock(9l,300.00,"BMW","ConsumerServices"));
        stocks.add(new Stock(10l,310.00,"BMW","ConsumerServices"));
        stocks.add(new Stock(11l,320.00,"BMW","ConsumerServices"));
        stocks.add(new Stock(12l,330.00,"BMW","ConsumerServices"));
        stocks.add(new Stock(13l,400.00,"Apple","Manufacturing"));
        stocks.add(new Stock(14l,410.00,"Apple","Manufacturing"));
        stocks.add(new Stock(15l,420.00,"Apple","Manufacturing"));
        stocks.add(new Stock(16l,430.00,"Apple","Manufacturing"));
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
