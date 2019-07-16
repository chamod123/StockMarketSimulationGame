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
                .match(BrokerMessages.CreateBrokerMessage.class, handleCreateBrocker())
                .match(BrokerMessages.GetBrokerMessage.class, handleGetBroker())
                .build();
    }


    private FI.UnitApply<BrokerMessages.CreateBrokerMessage> handleCreateBrocker() {
        return createBrokerMessage -> {
            brokerService.createBroker(createBrokerMessage.getBroker());
            sender().tell(new BrokerMessages.ActionPerformed(String.format("Broker %s created.", createBrokerMessage.getBroker()
                    .getName())), getSelf());
        };
    }
    private FI.UnitApply<BrokerMessages.GetBrokerMessage> handleGetBroker() {
        return getBrokerMessage -> {
            sender().tell(brokerService.getBroker(getBrokerMessage.getBrokerId()), getSelf());
        };
    }


}
