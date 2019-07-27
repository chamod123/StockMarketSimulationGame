package Messages;

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
//        private final int turns;

        public StartGameMessage() {
        }

//        public int getTurns() {
//            return turns;
//        }
    }
}
