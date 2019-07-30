package Messages;



import Model.Player;
import akka.actor.ActorRef;

import java.io.Serializable;

public interface PlayerMessages {

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

    class CreatePlayerMessage implements Serializable {

        private static final long serialVersionUID = 1L;
        private final Player player;
        private static ActorRef bankActor;

        public CreatePlayerMessage(Player player, ActorRef bankActor) {
            this.player = player;
            this.bankActor = bankActor;
        }

        public Player getPlayer() {
            return player;
        }

        public ActorRef getBankActor() {
            return bankActor;
        }
    }

    class GetPlayerMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Long playerId;

        public GetPlayerMessage(Long playerId) {
            this.playerId = playerId;
        }

        public Long getPlayerId() {
            return playerId;
        }
    }

    class LoginPlayerMessage implements Serializable {
        private final Long playerId;
        private final String password;

        public LoginPlayerMessage(Long playerId,String password) {
            this.playerId = playerId;
            this.password=password;
        }

        public Long getPlayerId() {
            return playerId;
        }

        public String getPassword() {
            return password;
        }
    }
}