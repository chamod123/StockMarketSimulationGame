package Actors;

import Messages.BrokerMessages;
import Messages.PlayerMessages;
import Service.BrokerService;
import Service.PlayerService;
import akka.actor.AbstractActor;
import akka.japi.pf.FI;

public class BrockerActor  extends AbstractActor {

    private BrokerService brokerService = new BrokerService();

    @Override
    public Receive createReceive() {


        return receiveBuilder()
                .match(BrokerMessages.CreateBrokerMessage.class, handleCreateUser())
                .match(BrokerMessages.GetBrokerMessage.class, handleGetUser())
                .build();
    }


    private FI.UnitApply<BrokerMessages.CreateBrokerMessage> handleCreateUser() {
        return createUserBrokerMessageMessage -> {
            brokerService.createBroker(createUserBrokerMessageMessage.getBroker());
            sender().tell(new BrokerMessages.ActionPerformed(String.format("Broker %s created.", createUserBrokerMessageMessage.getBroker()
                    .getName())), getSelf());
        };
    }
    private FI.UnitApply<BrokerMessages.GetBrokerMessage> handleGetUser() {
        return getBrokerMessageMessage -> {
            sender().tell(brokerService.getBroker(getBrokerMessageMessage.getBrokerId()), getSelf());
        };
    }


}
