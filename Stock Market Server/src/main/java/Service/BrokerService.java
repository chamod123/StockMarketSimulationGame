package Service;

import Model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrokerService {
    private final static List<Broker> brokers = new ArrayList<>();

    MarketService marketService;
    BankService bankService;
    public ArrayList<Player> stockAccounts;
    public ArrayList<Transaction> Transactions;

    static {
        brokers.add(new Broker(1l, "ChamodBroker"));
        brokers.add(new Broker(2l, "IndikaBroker"));
        brokers.add(new Broker(3l, "MelaniBroker"));
        brokers.add(new Broker(4l, "NipuniBroker"));
        brokers.add(new Broker(5l, "DilakaBroker"));
        brokers.add(new Broker(6l, "NiroshimaBroker"));
    }

    public Optional<Broker> getBroker(Long id) {
        return brokers.stream()
                .filter(broker -> broker.getId()
                        .equals(id))
                .findFirst();
    }

    public void createBroker(Broker user) {
        brokers.add(user);
    }

    public List<Broker> getBrokers() {
        return brokers;
    }


    //buy stock
    public Boolean buyStock(String name, String stock, int quantity) throws Exception {
        BigDecimal totalvalue=marketService.getStock(stock).getStockPrice().multiply(BigDecimal.valueOf(quantity));
        if (bankService.Balance(name).compareTo(totalvalue)>=0) {
            Integer count = GetPlayer(name).getStocks().get(stock);;
            if (count == null) {
                GetPlayer(name).getStocks().put(stock, quantity);
            }
            else {
                GetPlayer(name).getStocks().put(stock, count + quantity);
            }
            bankService.Withdraw(marketService.GetCurrentTurn(), name, stock, totalvalue);
            Transactions.add(new Transaction(name,marketService.GetCurrentTurn(),"BUY",stock,quantity,totalvalue));
            return true;
        }
        else {
            return false;
        }
    }


    //need get from the player actor
    //get player buy name
    public Player GetPlayer(String name) throws Exception {

        for (Player c : stockAccounts) {
            if (name.equals(c.getName())) {
                return c;
            }
        }
        throw new Exception("Player with the name "+name+" does not exsist");

    }
}
