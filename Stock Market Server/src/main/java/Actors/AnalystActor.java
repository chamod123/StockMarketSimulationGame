package Actors;

import Messages.AnalystMessages;
import Messages.BankMessages;
import Messages.BrokerMessages;
import Service.BankService;
import Service.BrokerService;
import Service.MarketService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class AnalystActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AnalystMessages.StartGameMessage.class, startGame())//start game
                .match(AnalystMessages.NextTurnMessage.class, nextTurn())//next turn
                .match(AnalystMessages.GetPredictionMessage.class, Prediction())//Prediction
                .match(AnalystMessages.GetCurrentTurnMessage.class, currentTurn())//get current turn
                .build();
    }

    //start game
    private FI.UnitApply<AnalystMessages.StartGameMessage> startGame() {
        return startGameMessage -> {
            //call to brocker actor to start game
            startGameMessage.getBrokerActor().tell(new BrokerMessages.StartGameMessage(),getSelf());
            sender().tell(new AnalystMessages.ActionPerformed(String.format("start game with turns "
            )), getSelf());
        };
    }

//    //next turn
    private FI.UnitApply<AnalystMessages.NextTurnMessage> nextTurn() {
        return nextTurnMessage -> {
            System.out.println("GetCurrentTurn " + MarketService.GetCurrentTurn());
            System.out.println("turns " + MarketService.turns);
            if(MarketService.GetCurrentTurn()==1){
                MarketService.setEvents();
                MarketService.setTrends();
//                MarketService.ChangeStockValues();
            }
            if(MarketService.GetCurrentTurn()<MarketService.turns) {
                //call broker actor to start game
                nextTurnMessage.getBrokerActor().tell(new BrokerMessages.NextTurnMessage(),getSelf());
                MarketService.NextTurn();
            }
            MarketService.GetCurrentTurn();
            sender().tell(new AnalystMessages.ActionPerformed(String.format("go to next turn "
            )), getSelf());
        };
    }

    //Prediction
    private FI.UnitApply<AnalystMessages.GetPredictionMessage> Prediction() {
        return getPredictionMessage -> {
            //call to broker actor to get Prediction
            getPredictionMessage.getBrokerActor().tell(new BrokerMessages.GetPredictionMessage(),getSelf());
            sender().tell(new AnalystMessages.ActionPerformed(String.format("get prediction "
            )), getSelf());
        };
    }

    //Prediction
    private FI.UnitApply<AnalystMessages.GetCurrentTurnMessage> currentTurn() {
        return currentTurnMessage -> {
            //call to broker actor to get Prediction
            currentTurnMessage.getBrokerActor().tell(new BrokerMessages.GetCurrentTurnMessage(),getSelf());
            sender().tell(new AnalystMessages.ActionPerformed(String.format("get prediction "
            )), getSelf());
        };
    }

}
