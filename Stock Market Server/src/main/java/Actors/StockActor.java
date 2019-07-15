package Actors;

import Messages.StockMessages;
import Service.StockService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class StockActor extends AbstractActor {
    private StockService stockService = new StockService();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StockMessages.CreateStockMessage.class, handleCreateStock())
                .match(StockMessages.GetStockMessage.class, handleGetStock())
                .match(StockMessages.GetStockSectorMessage.class, handleGetStockBySector())
                .build();
    }

    private FI.UnitApply<StockMessages.CreateStockMessage> handleCreateStock() {
        return createStockMessage -> {
            stockService.createStock(createStockMessage.getStock());
            sender().tell(new StockMessages.ActionPerformed(String.format("Stock %s added.", createStockMessage.getStock()
                    .getCompanyName())), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.GetStockMessage> handleGetStock() {
        return getStockMessage -> {
            sender().tell(stockService.getStock(getStockMessage.getStockId()), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.GetStockSectorMessage> handleGetStockBySector() {
        return getStockSectorMessage -> {
            sender().tell(stockService.getStockBySector(getStockSectorMessage.getSector()), getSelf());
        };
    }

}
