package Actors;

import Messages.BankMessages;
import Messages.BrokerMessages;
import Messages.GameMessage;
import Messages.PlayerMessages;
import Model.Transaction;
import Service.BrokerService;
import Service.MarketService;
import Service.PlayerService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

import java.math.BigDecimal;

public class BrokerActor extends AbstractActor {

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
                .match(BrokerMessages.GetWinnerMessage.class, getWinner())//get Winner
                .match(BrokerMessages.GetAllPlayerMessage.class, getAllPlayers())//get all Players
                .match(BrokerMessages.StartGameMessage.class, startGame())//Start game
                .match(BrokerMessages.NextTurnMessage.class, nextTurn())//Next turn
                .match(BrokerMessages.GetPredictionMessage.class, Prediction())//prediction
                .match(BrokerMessages.GetCurrentTurnMessage.class, currentTurn())//get current turn
                .match(BrokerMessages.AddPlayerToGameMessage.class, addPlayerToGame())//add player to game
                .match(BrokerMessages.GetAllStocksMessage.class, getAllStocksMessage())//get all transaction Data
//                .match(BrokerMessages.GetallPlayerInGameMessage.class, handleGetallPlayer())//get all player in game
                .build();
    }


//    //get all players in game
//    private FI.UnitApply<BrokerMessages.GetallPlayerInGameMessage> handleGetallPlayer() {
//        return getallPlayerMessage -> {
//            sender().tell(BrokerService.getAllPlayer(), getSelf());
//        };
//    }

    private FI.UnitApply<BrokerMessages.CreateBrokerMessage> handleCreateBrocker() {
        return createBrokerMessage -> {
            BrokerService.createBroker(createBrokerMessage.getBroker());
            sender().tell(new BrokerMessages.ActionPerformed(String.format("Broker %s created.", createBrokerMessage.getBroker()
                    .getName())), getSelf());
        };
    }

    private FI.UnitApply<BrokerMessages.GetBrokerMessage> handleGetBroker() {
        return getBrokerMessage -> {
            sender().tell(BrokerService.getBroker(getBrokerMessage.getBrokerId()), getSelf());
        };
    }

    //checked
    //buy Stock
    private FI.UnitApply<BrokerMessages.BuyStockMessage> handleBuyStock() {
        return getBrokerMessage -> {
            //get stock Price*Quantity from stock item in market
            BigDecimal totalvalue = MarketService.getStock(getBrokerMessage.getMarket().getStock()).getStockPrice().multiply(BigDecimal.valueOf(getBrokerMessage.getMarket().getQuantity()));
            //passe username,stock, quantity to buy the stock for that user
            boolean done = BrokerService.buyStock(getBrokerMessage.getMarket().getUsername(), getBrokerMessage.getMarket().getStock(), getBrokerMessage.getMarket().getQuantity(), totalvalue);
            if (done) {
                //Withdraw
                // pass the name, amount
                getBrokerMessage.getBankActor().tell(new BankMessages.WithdrawMessage(new Transaction(getBrokerMessage.getMarket().getUsername(), totalvalue)), getSelf());
            }
        };
    }

    //checked
    //sell Stock
    private FI.UnitApply<BrokerMessages.SellStockMessage> handleSellStock() {
        return sellStockMessage -> {
            BigDecimal totalvalue = MarketService.getStock(sellStockMessage.getMarket().getStock()).getStockPrice().multiply(BigDecimal.valueOf(sellStockMessage.getMarket().getQuantity()));
            System.out.println("totalvalue" + totalvalue + ": stock :" + MarketService.getStock(sellStockMessage.getMarket().getStock()).getStockPrice() + ": stock :" + BigDecimal.valueOf(sellStockMessage.getMarket().getQuantity()));
            //passe username,stock, quantity to sell the stock for that user
            boolean done = BrokerService.selltock(sellStockMessage.getMarket().getUsername(), sellStockMessage.getMarket().getStock(), sellStockMessage.getMarket().getQuantity(), totalvalue);
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

            sender().tell(BrokerService.GetTotalStockValue(getTotalStockValueMessage.getName().toString()), getSelf());

        };
    }

    //get Portofolio
    private FI.UnitApply<BrokerMessages.GetPortofolioMessage> handleGetPortofolio() {
        return getPortofolioMessage -> {
            sender().tell(BrokerService.GetPlayer(getPortofolioMessage.getName()).GetPortofolio(), getSelf());
        };
    }

    //get all transaction Data
    private FI.UnitApply<BrokerMessages.GetAllTransactionsMessage> getAllTransactions() {
        return getAllTransactionsMessage -> {
            sender().tell(BrokerService.getTransactions(), getSelf());
        };
    }

    //get winner
    private FI.UnitApply<BrokerMessages.GetWinnerMessage> getWinner() {
        return getWinnerMessage -> {
            sender().tell(BrokerService.GetWinner(), getSelf());
        };
    }

    //get All Players
    private FI.UnitApply<BrokerMessages.GetAllPlayerMessage> getAllPlayers() {
        return getAllPlayerMessage -> {
            sender().tell(BrokerService.GetAllPlayers(), getSelf());
        };
    }

    //start game
    private FI.UnitApply<BrokerMessages.StartGameMessage> startGame() {
        return startGameMessage -> {
            //need to complete
//            sender().tell(brokerService.CreateAccount("Computer"), getSelf());
        };
    }

    //next Turn
    private FI.UnitApply<BrokerMessages.NextTurnMessage> nextTurn() {
        return nextTurnMessage -> {
//            BrokerService.ComputerPlay();
            System.out.println("Computer Player need to complete");
            sender().tell(new BrokerMessages.ActionPerformed(String.format("Computer Player created")), getSelf());
        };
    }

    //get Prediction
    private FI.UnitApply<BrokerMessages.GetPredictionMessage> Prediction() {
        return getPredictionMessage -> {
            sender().tell(BrokerService.Prediction(), getSelf());
        };
    }

    //get Prediction
    private FI.UnitApply<BrokerMessages.GetCurrentTurnMessage> currentTurn() {
        return getPredictionMessage -> {
//            MarketService.GetCurrentTurn();
            sender().tell(MarketService.GetCurrentTurn(), getSelf());
        };
    }

    private FI.UnitApply<BrokerMessages.AddPlayerToGameMessage> addPlayerToGame() {

        return playerToGameMessage -> {
            sender().tell(BrokerService.CreateAccount(playerToGameMessage.getPlayer()), getSelf());
        };
    }

    //get all Stocks for grapgh Data
    private FI.UnitApply<BrokerMessages.GetAllStocksMessage> getAllStocksMessage() {
        return getAllStocksMessage -> {
            sender().tell(MarketService.getStocksPerGraph(), getSelf());
        };
    }
}
