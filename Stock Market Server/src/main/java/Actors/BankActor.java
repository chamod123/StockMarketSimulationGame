package Actors;

import Messages.BankMessages;
import Model.Account;
import Service.BankService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class BankActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(BankMessages.CreateAccountMessage.class, createAccount())
                .match(BankMessages.WithdrawMessage.class, withdraw())//Withdraw from player
                .match(BankMessages.DepositMessage.class, deposit())//Deposit from player
                .match(BankMessages.GetBankBalanceMessage.class, getBankBalance())//get Bank Balance for a player
                .build();
    }

    private FI.UnitApply<BankMessages.CreateAccountMessage> createAccount() {
        return createAccountMessage -> {
            BankService.CreateAccount(createAccountMessage.getAccount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Player %s Account created.", createAccountMessage.getAccount().getPlayerId()
            )), getSelf());
        };
    }

    //Withdraw from player
    private FI.UnitApply<BankMessages.WithdrawMessage> withdraw() {
        return withdrawMessage -> {
            //Withdraw from player
            BankService.Withdraw(withdrawMessage.getTransaction().getName(),withdrawMessage.getTransaction().getAmount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Withdraw for %s .", withdrawMessage.getTransaction().getName()
            )), getSelf());
        };
    }

    //Deposit from player
    private FI.UnitApply<BankMessages.DepositMessage> deposit() {
        return depositMessage -> {
            //Withdraw from player
            BankService.Deposit(depositMessage.getTransaction().getName(),depositMessage.getTransaction().getAmount());
            sender().tell(new BankMessages.ActionPerformed(String.format("Withdraw for %s .", depositMessage.getTransaction().getName()
            )), getSelf());
        };
    }

    //get Bank Balance for a player
    private FI.UnitApply<BankMessages.GetBankBalanceMessage> getBankBalance() {
        return getBankBalanceMessage -> {
            sender().tell(BankService.Balance(getBankBalanceMessage.getName()),getSelf());
        };
    }


}
