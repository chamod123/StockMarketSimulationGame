package Actors;

import Messages.BrokerMessages;
import Messages.PlayerMessages;
import Service.BrokerService;
import Service.MarketService;
import Service.PlayerService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

import java.math.BigDecimal;

public class BrokerActor extends AbstractActor {
    MarketService marketService;
    private BrokerService brokerService = new BrokerService();

    @Override
    public Receive createReceive() {


        return receiveBuilder()
                .match(BrokerMessages.CreateBrokerMessage.class, handleCreateBrocker())//create broker
                .match(BrokerMessages.GetBrokerMessage.class, handleGetBroker())//get broker
                .match(BrokerMessages.BuyStockMessage.class, handleBuyStock())//buy Stock
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
    private FI.UnitApply<BrokerMessages.BuyStockMessage> handleBuyStock() {
        return getBrokerMessage -> {
            //get stock Price*Quantity from stock item in market
            BigDecimal totalvalue=marketService.getStock(getBrokerMessage.getMarket().getStock()).getStockPrice().multiply(BigDecimal.valueOf(getBrokerMessage.getMarket().getQuantity()));
            //passe username,stock, quantity to buy the stock for that user
            boolean done = brokerService.buyStock(getBrokerMessage.getMarket().getUsername(),getBrokerMessage.getMarket().getStock(),getBrokerMessage.getMarket().getQuantity(),totalvalue);

            sender().tell(true, getSelf());
        };
    }


}
