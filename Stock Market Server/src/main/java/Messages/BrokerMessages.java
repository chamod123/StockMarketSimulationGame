package Messages;


import Model.Broker;
import Model.Market;
import Model.Player;
import akka.actor.ActorRef;

import java.io.Serializable;

public interface BrokerMessages {

    class ActionPerformed implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String description;

        public ActionPerformed(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    class CreateBrokerMessage implements Serializable {

        private static final long serialVersionUID = 1L;
        private final Broker broker;

        public CreateBrokerMessage(Broker broker) {
            this.broker = broker;
        }

        public Broker getBroker() {
            return broker;
        }
    }

    class GetBrokerMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Long brokerId;

        public GetBrokerMessage(Long brokerId) {
            this.brokerId = brokerId;
        }

        public Long getBrokerId() {
            return brokerId;
        }
    }
    //get Bank Balance for a player
    class GetGraphnameMessage implements Serializable {
        private final String name;

        public GetGraphnameMessage(String name) {
            this.name=name;
        }

        public String getName() {
            return name;
        }
    }
    class BuyStockMessage implements Serializable {
        private Market market;
        private static ActorRef bankActor;

        public BuyStockMessage(Market market, ActorRef bankActor) {
            this.market = market;
            this.bankActor = bankActor;
        }

        public Market getMarket() {
            return market;
        }

        public void setMarket(Market market) {
            this.market = market;
        }

        public static ActorRef getBankActor() {
            return bankActor;
        }

        public static void setBankActor(ActorRef bankActor) {
            BuyStockMessage.bankActor = bankActor;
        }
    }

    class SellStockMessage implements Serializable {
        private Market market;
        private static ActorRef bankActor;

        public SellStockMessage(Market market, ActorRef bankActor) {
            this.market = market;
            this.bankActor = bankActor;
        }

        public Market getMarket() {
            return market;
        }

        public void setMarket(Market market) {
            this.market = market;
        }

        public static ActorRef getBankActor() {
            return bankActor;
        }

        public static void setBankActor(ActorRef bankActor) {
            BuyStockMessage.bankActor = bankActor;
        }
    }

    class GetTotalStockValueMessage implements Serializable {
        private final String name;

        public GetTotalStockValueMessage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class GetPortofolioMessage implements Serializable {
        private final String name;

        public GetPortofolioMessage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class GetAllTransactionsMessage implements Serializable {
        public GetAllTransactionsMessage() {
        }
    }
    class GetAllStocksMessage implements Serializable {
        public GetAllStocksMessage() {
        }
    }

    class GetWinnerMessage implements Serializable {
        public GetWinnerMessage() {
        }
    }

    class GetAllPlayerMessage implements Serializable {
        public GetAllPlayerMessage() {
        }
    }

    class NextTurnMessage implements Serializable {
        public NextTurnMessage() {
        }
    }

    class StartGameMessage implements Serializable {
        public StartGameMessage() {
        }
    }

    class GetPredictionMessage implements Serializable {
        public GetPredictionMessage() {
        }
    }

    //get current turn
    class GetCurrentTurnMessage implements Serializable {

        public GetCurrentTurnMessage() {
        }

    }

    class AddPlayerToGameMessage implements Serializable {
        private final Player player;

        public AddPlayerToGameMessage(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }
    }

//    class GetallPlayerInGameMessage implements Serializable {
//        public GetallPlayerInGameMessage( ) {
//
//        }
//    }

}