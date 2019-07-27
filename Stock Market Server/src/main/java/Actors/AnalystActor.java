package Actors;

import Messages.AnalystMessages;
import Messages.BankMessages;
import Service.BankService;
import Service.BrokerService;
import Service.MarketService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class AnalystActor extends AbstractActor {
    private BrokerService brokerService = new BrokerService();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
        .match(AnalystMessages.StartGameMessage.class, startGame())//start game
                .build();
    }

    //start game
    private FI.UnitApply<AnalystMessages.StartGameMessage> startGame() {
        return startGameMessage -> {
            brokerService.CreateAccount("Computer");
            sender().tell(new AnalystMessages.ActionPerformed(String.format("start game with turns "
            )), getSelf());
        };
    }


}
