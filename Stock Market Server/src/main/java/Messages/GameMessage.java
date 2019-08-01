package Messages;

import Model.Player;
import akka.actor.ActorRef;

import java.io.Serializable;

public interface GameMessage {
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

    class AddPlayerToGameMessage implements Serializable {
        private final Long id;
        private static ActorRef playerActor;
        private static ActorRef brokerActor;

        public AddPlayerToGameMessage(Long id, ActorRef playerActor, ActorRef brokerActor) {
            this.id = id;
            this.playerActor=playerActor;
            this.brokerActor=brokerActor;
        }

        public Long getId() {
            return id;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }

        public static ActorRef getPlayerActor() {
            return playerActor;
        }
    }
}
