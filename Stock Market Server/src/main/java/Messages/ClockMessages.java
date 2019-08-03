package Messages;

import akka.actor.ActorRef;

import java.io.Serializable;

public interface ClockMessages {

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

    //Next turn
    class NextTurnMessage implements Serializable {
        private static ActorRef brokerActor;
        private static ActorRef playerAIActor;

        public NextTurnMessage(ActorRef brokerActor,ActorRef playerAIActor) {
            this.playerAIActor=playerAIActor;
            this.brokerActor = brokerActor;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }

        public static ActorRef getPlayerAIActor() {
            return playerAIActor;
        }
    }
}
