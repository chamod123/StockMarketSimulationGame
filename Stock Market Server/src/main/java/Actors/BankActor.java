package Actors;

import Messages.BankMessages;
import Model.Account;
import Service.BankService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class BankActor extends AbstractActor {
    private BankService bankService = new BankService();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(BankMessages.CreateAccountMessage.class, CreateAccount())
                .match(BankMessages.WithdrawMessage.class, Withdraw())//Withdraw from player
                .build();
    }

    private FI.UnitApply<BankMessages.CreateAccountMessage> CreateAccount() {
        return createAccountMessage -> {
            bankService.CreateAccount(createAccountMessage.getAccount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Player %s Account created.", createAccountMessage.getAccount().getPlayerId()
            )), getSelf());
        };
    }

    private FI.UnitApply<BankMessages.WithdrawMessage> Withdraw() {
        System.out.println("awa 7");
        return createAccountMessage -> {
            //Withdraw from player
            bankService.Withdraw(createAccountMessage.getTransaction().getName(),createAccountMessage.getTransaction().getAmount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Withdraw for %s .", createAccountMessage.getTransaction().getName()
            )), getSelf());
        };
    }


}
