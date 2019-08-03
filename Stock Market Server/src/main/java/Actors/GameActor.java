package Actors;

import Messages.BrokerMessages;
import Messages.GameMessage;
import Messages.PlayerMessages;
import Service.BrokerService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class GameActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GameMessage.AddPlayerToGameMessage.class, addPlayerToGame())//add player to game
                .match(GameMessage.GetallPlayerMessage.class, handleGetallPlayer())//get all player in game
                .build();
    }
    //get all player
    private FI.UnitApply<GameMessage.GetallPlayerMessage> handleGetallPlayer() {
        return getallPlayerMessage -> {
            sender().tell(BrokerService.getAllPlayer(), getSelf());
//            getallPlayerMessage.getBrokerActor().tell(new BrokerMessages.GetallPlayerInGameMessage(), getSelf());
        };
    }


    //add player to game
    private FI.UnitApply<GameMessage.AddPlayerToGameMessage> addPlayerToGame() {
        return  playerToGameMessage -> {
//            brokerService.CreateAccount(createPlayerMessage.getPlayer());
            playerToGameMessage.getPlayerActor().tell( new PlayerMessages.AddPlayerToGameMessage(playerToGameMessage.getId(),playerToGameMessage.getBrokerActor()), getSelf());
            sender().tell(new GameMessage.ActionPerformed("Player added"), getSelf());
        };
    }
}
