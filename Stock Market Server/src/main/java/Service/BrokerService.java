package Service;

import Model.*;

import java.math.BigDecimal;
import java.util.*;

public class BrokerService {
    private final static List<Broker> brokers = new ArrayList<>();

    MarketService marketService = new MarketService();
    BankService bankService = new BankService();
    public ArrayList<Player> stockAccounts = new ArrayList<>();
    public ArrayList<Transaction> Transactions = new ArrayList<>();

    static {
        brokers.add(new Broker(1l, "ChamodBroker"));
        brokers.add(new Broker(2l, "IndikaBroker"));
        brokers.add(new Broker(3l, "MelaniBroker"));
        brokers.add(new Broker(4l, "NipuniBroker"));
        brokers.add(new Broker(5l, "DilakaBroker"));
        brokers.add(new Broker(6l, "NiroshimaBroker"));
    }

    public Player CreateAccount(String name){
        bankService.CreateAccount( new Account(name));
        Player newPlayer = new Player(name);
        stockAccounts.add(newPlayer);
        return newPlayer;

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
        System.out.println("awa 4  " + totalvalue + name);
        //user value need to grater than total value. then can buy stock
        if (bankService.Balance(name).compareTo(totalvalue) >= 0) {
            Integer count = GetPlayer(name).getStocks().get(stock);
            ;
            if (count == null) {// item has not buy from that item
                GetPlayer(name).getStocks().put(stock, quantity);
            } else {// item has buy from that item
                GetPlayer(name).getStocks().put(stock, count + quantity);
            }
            Transactions.add(new Transaction(name, marketService.GetCurrentTurn(), "BUY", stock, quantity, totalvalue));
            return true;
        } else {
            return false;
        }

    }

    //sell stock
    public Boolean selltock(String name, String stock, int quantity, BigDecimal totalvalue) throws Exception {
        Integer stockCount = GetPlayer(name).getStocks().get(stock);
        if (stockCount != null && stockCount >= quantity) {
            GetPlayer(name).getStocks().put(stock, stockCount - quantity);
//            BigDecimal totalvalue=market.getStock(stock).getStockPrice().multiply(BigDecimal.valueOf(quantity));

            Transactions.add(new Transaction(name, marketService.GetCurrentTurn(), "SELL", stock, quantity, totalvalue));
            return true;
        } else
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
        throw new Exception("Player with the name " + name + " does not exsist");

    }

    //get stock value
    public BigDecimal GetTotalStockValue(String name) throws Exception {
        HashMap<String, Integer> stocks = GetPlayer(name).GetPortofolio();
        BigDecimal total = BigDecimal.valueOf(0);
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            total = total.add(marketService.getStock(entry.getKey()).getStockPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return total;
    }

    //get all transaction
    public ArrayList<Transaction> getTransactions() {
        return Transactions;
    }

    //get current winner name
    public Player GetWinner() throws Exception {
        Player winner = stockAccounts.get(0);
        for (Player c : stockAccounts) {
            BigDecimal value = GetTotalStockValue(c.getName()).add(bankService.Balance(c.getName()));
            BigDecimal Winnervalue = GetTotalStockValue(winner.getName()).add(bankService.Balance(winner.getName()));
            if (value.compareTo(Winnervalue) > 0) {
                winner = c;
            }
        }
        return winner;

    }

    //get all Players
    public HashMap<String, ArrayList<BigDecimal>> GetAllPlayers() throws Exception {
        HashMap<String, ArrayList<BigDecimal>> hashmap = new HashMap();
        for (Player c : stockAccounts) {
            ArrayList<BigDecimal> al = new ArrayList<>();
            al.add(GetTotalStockValue(c.getName()));
            al.add(bankService.Balance(c.getName()));
            hashmap.put(c.getName(), al);
        }
        return hashmap;
    }

    //computer play
    public void ComputerPlay() throws Exception {
        ArrayList<String> predictions = this.Prediction();
        Random ran = new Random();
        int number = ran.nextInt(7 - 1 + 1) + 1;
        if (bankService.Balance("Computer").compareTo(marketService.getStock(predictions.get(0)).getStockPrice().multiply(BigDecimal.valueOf(number))) > 0) {
            BigDecimal totalvalue = marketService.getStock("Computer").getStockPrice().multiply(BigDecimal.valueOf(number));
            this.buyStock("Computer", predictions.get(0), number,totalvalue);
        }
        Player p = GetPlayer("Computer");

        if (p.getStocks().get(predictions.get(1)) != null) {
            BigDecimal totalvalue = marketService.getStock("Computer").getStockPrice().multiply(BigDecimal.valueOf( p.getStocks().get(predictions.get(1))));
            selltock("Computer", predictions.get(1), p.getStocks().get(predictions.get(1)),totalvalue);
        } else if (bankService.Balance("Computer").compareTo(BigDecimal.valueOf(500)) < 0) {

            for (Map.Entry<String, Integer> entry : p.getStocks().entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                if (value > 0) {
                    BigDecimal totalvalue1 = marketService.getStock("Computer").getStockPrice().multiply(BigDecimal.valueOf(value));
                    selltock("Computer", key, value,totalvalue1);
                    break;
                }
            }
        }
    }

    //return min and max stock in future or current turn
    public ArrayList<String> Prediction() {
        ArrayList<Stock> futureStocks = marketService.GetPredictedStocks();
        String max = futureStocks.get(12).getCompanyName();
        BigDecimal valMax = futureStocks.get(12).getStockPrice();
        String min = futureStocks.get(12).getCompanyName();
        BigDecimal valMin = futureStocks.get(12).getStockPrice();
        for (int i = 12; i < futureStocks.size(); i++) {
            if (futureStocks.get(i).getStockPrice().compareTo(valMax) > 0) {
                max = futureStocks.get(i - 12).getCompanyName();
                valMax = futureStocks.get(i).getStockPrice();

            }

        }
        for (int i = 12; i < futureStocks.size(); i++) {
            if (futureStocks.get(i).getStockPrice().compareTo(valMin) < 0) {
                min = futureStocks.get(i - 12).getCompanyName();
                valMin = futureStocks.get(i).getStockPrice();

            }

        }
        ArrayList<String> prediction = new ArrayList<>();
        prediction.add(max);
        prediction.add(min);
        return prediction;

    }


}
