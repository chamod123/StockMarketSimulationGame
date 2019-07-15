package Messages;



import Model.Broker;
import Model.Player;

import java.io.Serializable;

public interface BrokerMessages {

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

    class CreateBrokerMessage implements Serializable {

        private static final long serialVersionUID = 1L;
        private final Broker broker;

        public CreateBrokerMessage(Broker broker) {
            this.broker = broker;
        }

        public Broker getBroker() {
            return broker;
        }
    }

    class GetBrokerMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        private final Long brokerId;

        public GetBrokerMessage(Long brokerId) {
            this.brokerId =brokerId ;
        }

        public Long getBrokerId() {
            return brokerId;
        }
    }
}