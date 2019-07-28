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
    private BrokerService brokerService = new BrokerService();
    private MarketService marketService = new MarketService();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AnalystMessages.StartGameMessage.class, startGame())//start game
                .match(AnalystMessages.NextTurnMessage.class, nextTurn())//next turn
//                .match(AnalystMessages.GetPredictionMessage.class, Prediction())//next turn
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

    //next turn
    private FI.UnitApply<AnalystMessages.NextTurnMessage> nextTurn() {
        return nextTurnMessage -> {
            if(marketService.GetCurrentTurn()<marketService.turns) {
                //call to brocker actor to start game
                nextTurnMessage.getBrokerActor().tell(new BrokerMessages.NextTurnMessage(),getSelf());
                marketService.NextTurn();
            }
            marketService.GetCurrentTurn();
            sender().tell(new AnalystMessages.ActionPerformed(String.format("go to next turn "
            )), getSelf());
        };
    }

}
