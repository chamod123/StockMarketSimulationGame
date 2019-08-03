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
                .match(AnalystMessages.GetPredictionMessage.class, Prediction())//Prediction
                .build();
    }

    //start game
    private FI.UnitApply<AnalystMessages.StartGameMessage> startGame() {
        return startGameMessage -> {
            //call to brocker actor to start game
            startGameMessage.getBrokerActor().tell(new BrokerMessages.StartGameMessage(), getSelf());
            sender().tell(new AnalystMessages.ActionPerformed(String.format("start game with turns "
            )), getSelf());
        };
    }

    //Prediction
    private FI.UnitApply<AnalystMessages.GetPredictionMessage> Prediction() {
        return getPredictionMessage -> {
            //call to broker actor to get Prediction
//            getPredictionMessage.getBrokerActor().tell(new BrokerMessages.GetPredictionMessage(), getSelf());
            sender().tell(BrokerService.Prediction(), getSelf());

//            sender().tell(new AnalystMessages.ActionPerformed(String.format("get prediction "
//            )), getSelf());
        };
    }

//    //current Turn
//    private FI.UnitApply<AnalystMessages.GetCurrentTurnMessage> currentTurn() {
//        return currentTurnMessage -> {
//            //call to broker actor to get Prediction
//            currentTurnMessage.getBrokerActor().tell(new BrokerMessages.GetCurrentTurnMessage(),getSelf());
//            sender().tell(new AnalystMessages.ActionPerformed(String.format("get prediction "
//            )), getSelf());
//        };
//    }

}
