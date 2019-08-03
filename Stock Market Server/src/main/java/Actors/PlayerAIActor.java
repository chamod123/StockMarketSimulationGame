package Actors;

import Messages.PlayerAIActorMessage;
import Service.BrokerService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class PlayerAIActor  extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PlayerAIActorMessage.CreateAIPlayerMessage.class, ComputerPlay())//create AI Player BrokerService.ComputerPlay();
                .build();
    }

    //next Turn
    private FI.UnitApply<PlayerAIActorMessage.CreateAIPlayerMessage> ComputerPlay() {
        return nextTurnMessage -> {
            BrokerService.ComputerPlay();
        };
    }
}
