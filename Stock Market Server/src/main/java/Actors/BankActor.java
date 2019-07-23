package Actors;

import Messages.BankMessages;
import Model.Account;
import Service.BankService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class BankActor extends AbstractActor {
    private BankService playerService = new BankService();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(BankMessages.CreateAccountMessage.class, CreateAccount())
                .build();
    }

    private FI.UnitApply<BankMessages.CreateAccountMessage> CreateAccount() {
        return createAccountMessage -> {
            playerService.CreateAccount(createAccountMessage.getAccount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Player %s Account created.", createAccountMessage.getAccount().getPlayerId()
            )), getSelf());
        };
    }


}
