package Actors;


import Messages.BrokerMessages;
import Messages.ClockMessages;
import Messages.PlayerAIActorMessage;
import Service.MarketService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class ClockActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ClockMessages.NextTurnMessage.class, nextTurn())//next turn
                .build();
    }

    //    //next turn
    private FI.UnitApply<ClockMessages.NextTurnMessage> nextTurn() {
        return nextTurnMessage -> {
            System.out.println("GetCurrentTurn " + MarketService.GetCurrentTurn());
            if (MarketService.GetCurrentTurn() == 1) {
                MarketService.setEvents();
                MarketService.setTrends();
            }
            if (MarketService.GetCurrentTurn() < MarketService.turns) {
                //call broker actor to start game
                nextTurnMessage.getBrokerActor().tell(new BrokerMessages.NextTurnMessage(), getSelf());
                nextTurnMessage.getPlayerAIActor().tell(new PlayerAIActorMessage.CreateAIPlayerMessage(), getSelf());
                MarketService.NextTurn();

            }
            MarketService.GetCurrentTurn();
            sender().tell(new ClockMessages.ActionPerformed(String.format("go to next turn "
            )), getSelf());
        };
    }
}
