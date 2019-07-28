package com;

import Actors.*;
import ServerHttp.ServerActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorSystemCreate {
    public static ActorRef server;
    public static ActorRef playerActor;
    public static ActorRef brokerActor;
    public static ActorRef stockActor;
    public static ActorRef bankActor;
    public static ActorRef analystActor;

public ActorSystemCreate() {
    ActorSystem system = ActorSystem.create("ServerHttp");
    server = system.actorOf(ServerActor.props());
    playerActor = system.actorOf(Props.create(PlayerActor.class), "playerActor");
    brokerActor = system.actorOf(Props.create(BrokerActor.class), "brokerActor");
    stockActor = system.actorOf(Props.create(StockActor.class), "stockActor");
    bankActor = system.actorOf(Props.create(BankActor.class), "bankActor");
    analystActor = system.actorOf(Props.create(AnalystActor.class), "analystActor");
}

    public static ActorRef getPlayerActor() {
        return playerActor;
    }

    public static void setPlayerActor(ActorRef playerActor) {
        ActorSystemCreate.playerActor = playerActor;
    }

    public static ActorRef getBrokerActor() {
        return brokerActor;
    }

    public static void setBrokerActor(ActorRef brokerActor) {
        ActorSystemCreate.brokerActor = brokerActor;
    }

    public static ActorRef getStockActor() {
        return stockActor;
    }

    public static void setStockActor(ActorRef stockActor) {
        ActorSystemCreate.stockActor = stockActor;
    }

    public static ActorRef getBankActor() {
        return bankActor;
    }

    public static void setBankActor(ActorRef bankActor) {
        ActorSystemCreate.bankActor = bankActor;
    }

    public static ActorRef getAnalystActor() {
        return analystActor;
    }

    public static void setAnalystActor(ActorRef analystActor) {
        ActorSystemCreate.analystActor = analystActor;
    }

    public static ActorRef getServer() {
        return server;
    }

    public static void setServer(ActorRef server) {
        ActorSystemCreate.server = server;
    }
}
