package Messages;

import java.io.Serializable;

public interface PlayerAIActorMessage{

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

    class CreateAIPlayerMessage implements Serializable {

    }

}
