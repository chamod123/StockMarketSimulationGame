package Actors;

import Messages.StockMessages;
import Service.MarketService;
import Service.StockService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class StockActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StockMessages.CreateStockMessage.class, handleCreateStock())
                .match(StockMessages.GetStockMessage.class, handleGetStock())
                .match(StockMessages.GetStockSectorMessage.class, handleGetStockBySector())
                .match(StockMessages.GetAllStockMessage.class, handleGetAllStock())//get all stock
                .match(StockMessages.GetCurrentEventMessage.class, currentEvents())//get current event
                .match(StockMessages.UpdateStockMessage.class, updateStock())
                .build();
    }

    private FI.UnitApply<StockMessages.CreateStockMessage> handleCreateStock() {
        return createStockMessage -> {
            StockService.createStock(createStockMessage.getStock());
            sender().tell(new StockMessages.ActionPerformed(String.format("Stock %s added.", createStockMessage.getStock()
                    .getCompanyName())), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.UpdateStockMessage> updateStock() {
        return updateStockMessage -> {
            StockService.updateStock(updateStockMessage.getStock());
            sender().tell(new StockMessages.ActionPerformed(String.format("Stock %s updated.", updateStockMessage.getStock()
                    .getCompanyName())), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.GetStockMessage> handleGetStock() {
        return getStockMessage -> {
            sender().tell(StockService.getStock(getStockMessage.getStockId()), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.GetStockSectorMessage> handleGetStockBySector() {
        return getStockSectorMessage -> {
            sender().tell(StockService.getStockBySector(getStockSectorMessage.getSector()), getSelf());
        };
    }

    private FI.UnitApply<StockMessages.GetAllStockMessage> handleGetAllStock() {
        return getAllStockMessage -> {
            sender().tell(MarketService.getStocks(), getSelf());
        };
    }

    //get current event
    private FI.UnitApply<StockMessages.GetCurrentEventMessage> currentEvents() {
        return currentEventMessage -> {
            sender().tell(MarketService.GetCurrentEvents(), getSelf());
        };
    }

}
