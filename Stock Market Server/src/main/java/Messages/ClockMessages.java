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

        public NextTurnMessage(ActorRef brokerActor) {
            this.brokerActor = brokerActor;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }
    }
}
