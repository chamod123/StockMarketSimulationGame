package Messages;



import Model.Player;

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

        public CreatePlayerMessage(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return player;
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