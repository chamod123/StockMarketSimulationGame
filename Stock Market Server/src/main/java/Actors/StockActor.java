package Actors;

import Messages.StockMessages;
import Service.StockService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class StockActor   extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StockMessages.CreateStockMessage.class, handleCreateUser())
                .match(StockMessages.GetStockMessage.class, handleGetUser())
                .build();
    }

    private FI.UnitApply<StockMessages.CreateStockMessage> handleCreateUser() {
        return createStockMessageMessage -> {
            StockService.createStock(createStockMessageMessage.getStock());
            sender().tell(new StockMessages.ActionPerformed(String.format("Stock %s added.", createStockMessageMessage.getStock()
                    .getCompanyName())), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.GetStockMessage> handleGetUser() {
        return getPlayerMessageMessage -> {
            sender().tell(StockService.getStock(getPlayerMessageMessage.getStockId()), getSelf());
        };
    }

}
