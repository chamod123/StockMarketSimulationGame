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
                .match(BankMessages.DepositMessage.class, Deposit())//Deposit from player
                .build();
    }

    private FI.UnitApply<BankMessages.CreateAccountMessage> CreateAccount() {
        return createAccountMessage -> {
            bankService.CreateAccount(createAccountMessage.getAccount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Player %s Account created.", createAccountMessage.getAccount().getPlayerId()
            )), getSelf());
        };
    }

    //Withdraw from player
    private FI.UnitApply<BankMessages.WithdrawMessage> Withdraw() {
        System.out.println("awa 7");
        return withdrawMessage -> {
            //Withdraw from player
            bankService.Withdraw(withdrawMessage.getTransaction().getName(),withdrawMessage.getTransaction().getAmount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Withdraw for %s .", withdrawMessage.getTransaction().getName()
            )), getSelf());
        };
    }

    //Deposit from player
    private FI.UnitApply<BankMessages.DepositMessage> Deposit() {
        System.out.println("awa 7");
        return depositMessage -> {
            //Withdraw from player
            bankService.Deposit(depositMessage.getTransaction().getName(),depositMessage.getTransaction().getAmount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Withdraw for %s .", depositMessage.getTransaction().getName()
            )), getSelf());
        };
    }


}
