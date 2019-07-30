package Actors;

import Messages.BankMessages;
import Messages.PlayerMessages;
import Model.Account;
import Service.PlayerService;
import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.FI;

public class PlayerActor extends AbstractActor {
    private PlayerService playerService = new PlayerService();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PlayerMessages.CreatePlayerMessage.class, handleCreatePlayer())
                .match(PlayerMessages.GetPlayerMessage.class, handleGetPlayer())
                .match(PlayerMessages.LoginPlayerMessage.class, loginPlayer())
                .build();
    }

    private FI.UnitApply<PlayerMessages.CreatePlayerMessage> handleCreatePlayer() {
        return createPlayerMessage -> {
            playerService.createPlayer(createPlayerMessage.getPlayer());
            //Create Bank Account for Player
            createPlayerMessage.getBankActor().tell(new BankMessages.CreateAccountMessage(new Account(createPlayerMessage.getPlayer().getName())), getSelf());

            sender().tell(new PlayerMessages.ActionPerformed(String.format("Player %s created.", createPlayerMessage.getPlayer()
                    .getName())), getSelf());
        };
    }

    //get player
    private FI.UnitApply<PlayerMessages.GetPlayerMessage> handleGetPlayer() {
        return getPlayerMessage -> {
            sender().tell(playerService.getPlayer(getPlayerMessage.getPlayerId()), getSelf());
        };
    }

    //login player
    private FI.UnitApply<PlayerMessages.LoginPlayerMessage> loginPlayer() {
        return loginPlayerMessage -> {
            sender().tell(playerService.loginPlayer(loginPlayerMessage.getPlayerId(),loginPlayerMessage.getPassword()), getSelf());
        };
    }
}
