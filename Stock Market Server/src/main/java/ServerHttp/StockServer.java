package ServerHttp;

import Actors.BrockerActor;
import Actors.PlayerActor;
import Messages.BrokerMessages;
import Actors.StockActor;
import Messages.PlayerMessages;
import Model.Broker;
import Messages.StockMessages;
import Model.Player;
import Model.Stock;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.sysmsg.Create;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.StatusCode;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.pattern.PatternsCS;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import static akka.http.javadsl.server.PathMatchers.*;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class StockServer extends AllDirectives {
    private static final String RESOURCE_PATH = "Stock Market Server/src/main/resources/pages/";
    private Duration timeout = Duration.ofSeconds(180);
    private ActorRef server;
    private static ActorRef playerActor;
    private static ActorRef brokerActor;

    private static ActorRef stockActor;

    public StockServer(ActorSystem system) {
        server = system.actorOf(ServerActor.props());
    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("ServerHttp");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        StockServer app = new StockServer(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);
        System.out.println("Server online at http://localhost:8080/");
        playerActor = system.actorOf(Props.create(PlayerActor.class), "playerActor");
        brokerActor = system.actorOf(Props.create(BrockerActor.class), "brokerActor");
        stockActor = system.actorOf(Props.create(StockActor.class), "stockActor");
    }

    protected Route createRoute() {
        return route(
                path("players", this::postPlayer) //#POST - Create new Player
                , (path(segment("players").slash(longSegment()), id ->
                        route(getPlayer(id))))//#GET - get a Player Data
                , pathEndOrSingleSlash(() -> {
                    return complete("Hi");
                })
                ,path("stock", this::postStock)
                ,path(segment("brokers").slash(longSegment()),id -> route(getBroker(id))) //#GET - get a broker data
                ,path("brokers",this::postBroker) //#POST - Create Broker


        );
    }

    private Route postStock(){
        return route(post(() -> entity(Jackson.unmarshaller(Stock.class), stock -> {
            CompletionStage<StockMessages.ActionPerformed> stockCreated = Patterns.ask(stockActor, new StockMessages.CreateStockMessage(stock), timeout)
                    .thenApply(obj -> (StockMessages.ActionPerformed) obj);

            return onSuccess(() -> stockCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }





    //#POST - Create new Broker
    private Route postBroker() {
        return route(post(() -> entity(Jackson.unmarshaller(Broker.class), broker -> {
            CompletionStage<BrokerMessages.ActionPerformed> brokerCreated = Patterns.ask(brokerActor, new BrokerMessages.CreateBrokerMessage(broker), timeout)
                    .thenApply(obj -> (BrokerMessages.ActionPerformed) obj);

            return onSuccess(() -> brokerCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }
    //#GET - Get a broker Data
    private Route getBroker(Long id) {
        return get(() -> {
            CompletionStage<Optional<Broker>> broker = Patterns.ask(brokerActor, new BrokerMessages.GetBrokerMessage(id), timeout)
                    .thenApply(obj -> (Optional<Broker>) obj);
            return onSuccess(() -> broker,
                    performed -> {
                        if (performed.isPresent())
                            return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
    }



    //#POST - Create new Player
    private Route postPlayer() {
        return route(post(() -> entity(Jackson.unmarshaller(Player.class), player -> {
            CompletionStage<PlayerMessages.ActionPerformed> playerCreated = Patterns.ask(playerActor, new PlayerMessages.CreatePlayerMessage(player), timeout)
                    .thenApply(obj -> (PlayerMessages.ActionPerformed) obj);

            return onSuccess(() -> playerCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    //#GET - get a Player Data
    private Route getPlayer(Long id) {
        return get(() -> {
            CompletionStage<Optional<Player>> player = Patterns.ask(playerActor, new PlayerMessages.GetPlayerMessage(id), timeout)
                    .thenApply(obj -> (Optional<Player>) obj);
            return onSuccess(() -> player,
                    performed -> {
                        if (performed.isPresent())
                            return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
    }

}


