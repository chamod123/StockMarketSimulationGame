package Service;

import Model.*;

import java.math.BigDecimal;
import java.util.*;

public class BrokerService {
    private final static List<Broker> brokers = new ArrayList<>();

    MarketService marketService = new MarketService();
    BankService bankService = new BankService();
    public ArrayList<Player> stockAccounts = new ArrayList<>();
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
    public Boolean buyStock(String name, String stock, int quantity, BigDecimal totalvalue) throws Exception {
        System.out.println("awa 4  " + totalvalue +name);
        //user value need to grater than total value. then can buy stock
        if (bankService.Balance(name).compareTo(totalvalue)>=0) {
            Integer count = GetPlayer(name).getStocks().get(stock);;
            if (count == null) {// item has not buy from that item
                GetPlayer(name).getStocks().put(stock, quantity);
            }
            else {// item has buy from that item
                GetPlayer(name).getStocks().put(stock, count + quantity);
            }
            Transactions.add(new Transaction(name,marketService.GetCurrentTurn(),"BUY",stock,quantity,totalvalue));
            return true;
        }else {
            return false;
        }

    }

    //sell stock
    public Boolean selltock(String name, String stock, int quantity, BigDecimal totalvalue) throws Exception {
        Integer stockCount=GetPlayer(name).getStocks().get(stock);
        if (stockCount!=null && stockCount>=quantity) {
            GetPlayer(name).getStocks().put(stock, stockCount-quantity);
//            BigDecimal totalvalue=market.getStock(stock).getStockPrice().multiply(BigDecimal.valueOf(quantity));

            Transactions.add(new Transaction(name,marketService.GetCurrentTurn(),"SELL",stock,quantity,totalvalue));
            return true;
        }
        else
            return false;
    }


    //need get from the player actor
    //get player buy name
    public Player GetPlayer(String name) throws Exception {
        for (Player c : stockAccounts) {
            System.out.println("c.getName" + c.getName());
            if (name.equals(c.getName())) {
                return c;
            }
        }
        throw new Exception("Player with the name "+name+" does not exsist");

    }

    //get stock value
    public BigDecimal GetTotalStockValue(String name) throws Exception {
        HashMap<String,Integer> stocks=GetPlayer(name).GetPortofolio();
        BigDecimal total=BigDecimal.valueOf(0);
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            total=total.add(marketService.getStock(entry.getKey()).getStockPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return total;
    }




}
