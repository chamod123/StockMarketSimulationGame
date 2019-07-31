package Actors;

import Messages.GameMessage;
import Messages.PlayerMessages;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class GameActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GameMessage.AddPlayerToGameMessage.class, addPlayerToGame())//add player to game
                .build();
    }

    //add player to game
    private FI.UnitApply<GameMessage.AddPlayerToGameMessage> addPlayerToGame() {
        return  playerToGameMessage -> {
            System.out.println("Game actor 1");
//            brokerService.CreateAccount(createPlayerMessage.getPlayer());
            playerToGameMessage.getPlayerActor().tell( new PlayerMessages.AddPlayerToGameMessage(playerToGameMessage.getId(),playerToGameMessage.getBrokerActor()), getSelf());

            sender().tell(new GameMessage.ActionPerformed("Player %s created."), getSelf());
        };
    }
}
