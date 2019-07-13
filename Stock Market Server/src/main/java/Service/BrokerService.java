package Service;

import Model.Broker;
import Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BrokerService {
    private final static List<Broker> brokers = new ArrayList<>();

    static {
        brokers.add(new Broker(1l, "Chamod"));
        brokers.add(new Broker(2l, "Indika"));
        brokers.add(new Broker(3l, "Melani"));
        brokers.add(new Broker(4l, "Nipuni"));
        brokers.add(new Broker(5l, "Dilaka"));
        brokers.add(new Broker(6l, "Niroshima"));
    }

    public Optional<Broker> getBroker(Long id) {
        return brokers.stream()
                .filter(broker -> broker.getId()
                        .equals(id))
                .findFirst();
    }

    public void createBroker(Broker user) {
        brokers.add(user);
    }

    public List<Broker> getBrokers(){
        return brokers;
    }
}
