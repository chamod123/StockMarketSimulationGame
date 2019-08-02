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
        private final String userName;
        private final String password;

        public LoginPlayerMessage(String userName,String password) {
            this.userName = userName;
            this.password=password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }



    class AddPlayerToGameMessage implements Serializable {
        private final Long id;
        private static ActorRef brokerActor;

        public AddPlayerToGameMessage(Long id, ActorRef brokerActor) {
            this.id = id;
            this.brokerActor=brokerActor;
        }

        public Long getId() {
            return id;
        }

        public static ActorRef getBrokerActor() {
            return brokerActor;
        }
    }

    class UpdatePlayerMessage implements Serializable {
        private final Player player;

        public UpdatePlayerMessage(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
        }

    }


    class GetallPlayerMessage implements Serializable {
        private static final long serialVersionUID = 1L;

        public GetallPlayerMessage( ) {
        }

    }

}