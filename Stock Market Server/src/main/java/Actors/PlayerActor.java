package Actors;

import Messages.BankMessages;
import Messages.BrokerMessages;
import Messages.GameMessage;
import Messages.PlayerMessages;
import Model.Account;
import Service.BrokerService;
import Service.PlayerService;
import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;

public class PlayerActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PlayerMessages.CreatePlayerMessage.class, handleCreatePlayer())
                .match(PlayerMessages.GetPlayerMessage.class, handleGetPlayer())
                .match(PlayerMessages.LoginPlayerMessage.class, loginPlayer())
                .match(PlayerMessages.AddPlayerToGameMessage.class, addPlayerToGame())
                .build();
    }


    private FI.UnitApply<PlayerMessages.CreatePlayerMessage> handleCreatePlayer() {
        return createPlayerMessage -> {
            PlayerService.createPlayer(createPlayerMessage.getPlayer());
            //Create Bank Account for Player
            createPlayerMessage.getBankActor().tell(new BankMessages.CreateAccountMessage(new Account(createPlayerMessage.getPlayer().getName())), getSelf());

            sender().tell(new PlayerMessages.ActionPerformed(String.format("Player %s created.", createPlayerMessage.getPlayer()
                    .getName())), getSelf());
        };
    }

    //get player
    private FI.UnitApply<PlayerMessages.GetPlayerMessage> handleGetPlayer() {
        return getPlayerMessage -> {
            sender().tell(PlayerService.getPlayer(getPlayerMessage.getPlayerId()), getSelf());
        };
    }

    //login player
    private FI.UnitApply<PlayerMessages.LoginPlayerMessage> loginPlayer() {
        return loginPlayerMessage -> {
            sender().tell(PlayerService.loginPlayer(loginPlayerMessage.getUserName(),loginPlayerMessage.getPassword()), getSelf());
        };
    }


    //get player
    private FI.UnitApply<PlayerMessages.AddPlayerToGameMessage> addPlayerToGame() {

        return playerToGameMessage -> {
            playerToGameMessage.getBrokerActor().tell(new BrokerMessages.AddPlayerToGameMessage(PlayerService.getPlayer(playerToGameMessage.getId())), getSelf());
            sender().tell(new PlayerMessages.ActionPerformed(String.format("Player %s created.", playerToGameMessage.getId())), getSelf());
        };
    }
}
