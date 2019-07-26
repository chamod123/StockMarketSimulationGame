package Actors;

import Messages.BankMessages;
import Messages.BrokerMessages;
import Messages.PlayerMessages;
import Model.Transaction;
import Service.BrokerService;
import Service.MarketService;
import Service.PlayerService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

import java.math.BigDecimal;

public class BrokerActor extends AbstractActor {
    MarketService marketService = new MarketService();
    private BrokerService brokerService = new BrokerService();

    @Override
    public Receive createReceive() {


        return receiveBuilder()
                .match(BrokerMessages.CreateBrokerMessage.class, handleCreateBrocker())//create broker
                .match(BrokerMessages.GetBrokerMessage.class, handleGetBroker())//get broker
                .match(BrokerMessages.BuyStockMessage.class, handleBuyStock())//buy Stock
                .match(BrokerMessages.SellStockMessage.class, handleSellStock())//sell Stock
                .match(BrokerMessages.GetTotalStockValueMessage.class, handleGetStockTotalVal())//total value of Stock in a player
                .match(BrokerMessages.GetPortofolioMessage.class, handleGetPortofolio())//get Portofolio
                .match(BrokerMessages.GetAllTransactionsMessage.class, getAllTransactions())//get all transaction Data
                .match(BrokerMessages.GetWinnerMessage.class, getWinner())//get all transaction Data
                .build();
    }


    private FI.UnitApply<BrokerMessages.CreateBrokerMessage> handleCreateBrocker() {
        return createBrokerMessage -> {
            brokerService.createBroker(createBrokerMessage.getBroker());
            sender().tell(new BrokerMessages.ActionPerformed(String.format("Broker %s created.", createBrokerMessage.getBroker()
                    .getName())), getSelf());
        };
    }

    private FI.UnitApply<BrokerMessages.GetBrokerMessage> handleGetBroker() {
        return getBrokerMessage -> {
            sender().tell(brokerService.getBroker(getBrokerMessage.getBrokerId()), getSelf());
        };
    }

    //buy Stock
    private FI.UnitApply<BrokerMessages.BuyStockMessage> handleBuyStock() {
        System.out.println("awa 2");
        return getBrokerMessage -> {
            //get stock Price*Quantity from stock item in market
            BigDecimal totalvalue = marketService.getStock(getBrokerMessage.getMarket().getStock()).getStockPrice().multiply(BigDecimal.valueOf(getBrokerMessage.getMarket().getQuantity()));
            System.out.println("totalvalue" + totalvalue);

            //passe username,stock, quantity to buy the stock for that user
            boolean done = brokerService.buyStock(getBrokerMessage.getMarket().getUsername(), getBrokerMessage.getMarket().getStock(), getBrokerMessage.getMarket().getQuantity(), totalvalue);

            if (done) {
                //Withdraw
                // pass the name, amount
                getBrokerMessage.getBankActor().tell(new BankMessages.WithdrawMessage(new Transaction(getBrokerMessage.getMarket().getUsername(), totalvalue)), getSelf());
            }
        };
    }

    //sell Stock
    private FI.UnitApply<BrokerMessages.SellStockMessage> handleSellStock() {
        return sellStockMessage -> {
            BigDecimal totalvalue = marketService.getStock(sellStockMessage.getMarket().getStock()).getStockPrice().multiply(BigDecimal.valueOf(sellStockMessage.getMarket().getQuantity()));
            System.out.println("totalvalue" + totalvalue);

            //passe username,stock, quantity to buy the stock for that user
            boolean done = brokerService.selltock(sellStockMessage.getMarket().getUsername(), sellStockMessage.getMarket().getStock(), sellStockMessage.getMarket().getQuantity(), totalvalue);

            if (done) {
                //deposit
                // pass the name, amount
                sellStockMessage.getBankActor().tell(new BankMessages.DepositMessage(new Transaction(sellStockMessage.getMarket().getUsername(), totalvalue)), getSelf());
            }
        };
    }

    //sell Stock
    private FI.UnitApply<BrokerMessages.GetTotalStockValueMessage> handleGetStockTotalVal() {
        return getTotalStockValueMessage -> {
            //passe username,stock, quantity to buy the stock for that user

            sender().tell(brokerService.GetTotalStockValue(getTotalStockValueMessage.getName().toString()),getSelf());

        };
    }

    //get Portofolio
    private FI.UnitApply<BrokerMessages.GetPortofolioMessage> handleGetPortofolio() {
        return getPortofolioMessage -> {
            sender().tell(brokerService.GetPlayer(getPortofolioMessage.getName().toString()).GetPortofolio(),getSelf());
        };
    }

    //get all transaction Data
    private FI.UnitApply<BrokerMessages.GetAllTransactionsMessage> getAllTransactions() {
        return getAllTransactionsMessage -> {
            sender().tell(brokerService.getTransactions(),getSelf());
        };
    }

    //get all transaction Data
    private FI.UnitApply<BrokerMessages.GetWinnerMessage> getWinner() {
        return getWinnerMessage -> {
            sender().tell(brokerService.getTransactions(),getSelf());
        };
    }

}
