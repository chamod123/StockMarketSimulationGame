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
                .match(PlayerMessages.CreatePlayerMessage.class, handleCreateUser())
                .match(PlayerMessages.GetPlayerMessage.class, handleGetUser())
                .build();
    }

    private FI.UnitApply<PlayerMessages.CreatePlayerMessage> handleCreateUser() {
        return createUserPlayerMessageMessage -> {
            playerService.createPlayer(createUserPlayerMessageMessage.getPlayer());
            sender().tell(new PlayerMessages.ActionPerformed(String.format("Player %s created.", createUserPlayerMessageMessage.getPlayer()
                    .getName())), getSelf());
            //Create Bank Account for Player
            createUserPlayerMessageMessage.getBankActor().tell(new BankMessages.CreateAccountMessage(new Account(createUserPlayerMessageMessage.getPlayer().getName())), getSelf());
        };
    }

    private FI.UnitApply<PlayerMessages.GetPlayerMessage> handleGetUser() {
        return getPlayerMessageMessage -> {
            sender().tell(playerService.getPlayer(getPlayerMessageMessage.getPlayerId()), getSelf());
        };
    }
}
