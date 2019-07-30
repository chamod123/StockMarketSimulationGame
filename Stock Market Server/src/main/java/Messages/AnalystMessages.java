package Messages;

import akka.actor.ActorRef;

import java.io.Serializable;

public interface AnalystMessages {

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

    //start game
    class StartGameMessage implements Serializable {
        private static ActorRef brokerActor;

        public StartGameMessage(ActorRef brokerActor) {
            this.brokerActor = brokerActor;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }
    }

    //Next turn
    class NextTurnMessage implements Serializable {
        private static ActorRef brokerActor;

        public NextTurnMessage(ActorRef brokerActor) {
            this.brokerActor = brokerActor;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }
    }

    //get prediction
    class GetPredictionMessage implements Serializable {
        private static ActorRef brokerActor;

        public GetPredictionMessage(ActorRef brokerActor) {
            this.brokerActor = brokerActor;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }
    }

    //get current turn
    class GetCurrentTurnMessage implements Serializable {
        private static ActorRef brokerActor;

        public GetCurrentTurnMessage(ActorRef brokerActor) {
            this.brokerActor = brokerActor;
        }
        public static ActorRef getBrokerActor() {
            return brokerActor;
        }

    }
}
