package Service;

import Model.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {
    private final static List<Stock> stocks = new ArrayList<>();
// sectors : 1- Finance, 2-Technology, 3-ConsumerServices, 4-Manufacturing
//    static {
//        stocks.add(new Stock(1l, 100.00, "Google", 1l));
//
//        stocks.add(new Stock(5l, 200.00, "Mail", 2l));
//
//        stocks.add(new Stock(9l, 300.00, "Google", 3l));
//        stocks.add(new Stock(10l, 310.00, "Mail", 3l));
//        stocks.add(new Stock(11l, 320.00, "BMW", 3l));
//        stocks.add(new Stock(12l, 330.00, "Apple", 3l));
//
//        stocks.add(new Stock(13l, 400.00, "Google", 4l));
//        stocks.add(new Stock(14l, 410.00, "Mail", 4l));
//        stocks.add(new Stock(15l, 420.00, "BMW", 4l));
//        stocks.add(new Stock(16l, 430.00, "Apple", 4l));
//    }

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
