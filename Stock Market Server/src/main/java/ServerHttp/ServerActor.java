package ServerHttp;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ServerActor extends AbstractActor {
    public static Props props() {
        return Props.create(ServerActor.class);
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(ServerMessages.RequesServer.class, msg ->
                        getSender().tell(new ServerMessages.Server("Server Stating"), getSelf()))
                .build();
    }
}
