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
    public static ActorRef gameActor;
    public static ActorRef clockActor;

    public ActorSystemCreate() {
        ActorSystem system = ActorSystem.create("ServerHttp");
        server = system.actorOf(ServerActor.props());
        playerActor = system.actorOf(Props.create(PlayerActor.class), "playerActor");
        brokerActor = system.actorOf(Props.create(BrokerActor.class), "brokerActor");
        stockActor = system.actorOf(Props.create(StockActor.class), "stockActor");
        bankActor = system.actorOf(Props.create(BankActor.class), "bankActor");
        analystActor = system.actorOf(Props.create(AnalystActor.class), "analystActor");
        gameActor = system.actorOf(Props.create(GameActor.class), "gameActor");
        clockActor = system.actorOf(Props.create(ClockActor.class), "clockActor");
    }

    public static ActorRef getServer() {
        return server;
    }

    public static ActorRef getPlayerActor() {
        return playerActor;
    }

    public static ActorRef getBrokerActor() {
        return brokerActor;
    }

    public static ActorRef getStockActor() {
        return stockActor;
    }

    public static ActorRef getBankActor() {
        return bankActor;
    }

    public static ActorRef getAnalystActor() {
        return analystActor;
    }

    public static ActorRef getGameActor() {
        return gameActor;
    }

    public static ActorRef getClockActor() {
        return clockActor;
    }
}
