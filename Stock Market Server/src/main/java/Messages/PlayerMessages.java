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
            System.out.println("awa 2");
            System.out.println("awa 3" + player.getId() + player.getName());
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
        private final Long playerrId;

        public GetPlayerMessage(Long playerrId) {
            this.playerrId = playerrId;
        }

        public Long getPlayerId() {
            return playerrId;
        }
    }
}