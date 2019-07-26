package ServerHttp;

import Actors.BankActor;
import Actors.BrokerActor;
import Actors.PlayerActor;
import Actors.StockActor;
import Messages.BankMessages;
import Messages.BrokerMessages;
import Messages.PlayerMessages;
import Model.*;
import Messages.StockMessages;
import Model.Broker;
import Model.Stock;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import static akka.http.javadsl.server.PathMatchers.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class StockServer extends AllDirectives {
    private static final String RESOURCE_PATH = "Stock Market Server/src/main/resources/pages/";
    private Duration timeout = Duration.ofSeconds(180);
    private ActorRef server;
    private static ActorRef playerActor;
    private static ActorRef brokerActor;
    private static ActorRef stockActor;
    private static ActorRef bankActor;

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
        brokerActor = system.actorOf(Props.create(BrokerActor.class), "brokerActor");
        stockActor = system.actorOf(Props.create(StockActor.class), "stockActor");
        bankActor = system.actorOf(Props.create(BankActor.class), "bankActor");
    }

    protected Route createRoute() {
        return route(
                pathEndOrSingleSlash(() -> {
                    return complete("Hi");
                })
                , path("players", this::postPlayer) //#POST - Create new Player
                , path(segment("players").slash(longSegment()), id -> route(getPlayer(id)))//#GET - get a Player Data
                , path(segment("stock").slash(longSegment()), id -> route(getStock(id)))// #GET - get a stock Data
                , path("stock", this::postStock)// #POST - Create new stock
                , path(segment("stockBySector").slash(longSegment()), sectorId -> route(getStockBySector(sectorId)))// #GET - get stocks Data by sector
                , path(segment("brokers").slash(longSegment()), id -> route(getBroker(id))) //#GET - get a broker data
                , path("brokers", this::postBroker) //#POST - Create Broker
                , path("buyStock", this::buyStock) //#POST - buyStock
                , path("sellStock", this::sellStock) //#POST - sellStock
                , path(segment("allStock"),this::getAllStock)// #GET - get a stock Data
                , path(segment("stockValue").slash(longSegment()), id -> route(stockValue(id)))// #GET - get total stock value for player
                , path(segment("portofolio").slash(longSegment()), id -> route(getPortofolio(id)))// #GET - get getPortofolio
                , path(segment("bankBalance").slash(longSegment()), id -> route(getBankBalance(id)))// #GET - get Bank Balance for a player
        );
    }

    // #GET - get Bank Balance for a player
    private Route getBankBalance(Long id) {
        return get(() -> {
            CompletionStage<Optional<Bank>> bank = Patterns.ask(bankActor, new BankMessages.GetBankBalanceMessage(id), timeout)
                    .thenApply(obj -> (Optional<Bank>) obj);
            return onSuccess(() -> bank,
                    performed -> {
                        if (performed.isPresent())
                            return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
    }

    // #GET - get getPortofolio
    private Route getPortofolio(Long id) {
        return get(() -> {
            CompletionStage<HashMap<String, Integer>> player = Patterns.ask(brokerActor, new BrokerMessages.GetPortofolioMessage(id), timeout)
                    .thenApply(obj -> (HashMap<String, Integer>) obj);
            return onSuccess(() -> player,
                    performed -> {
                        if (player!=null)
                            return complete(StatusCodes.OK, performed, Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
    }


    // #GET - get total stock value for player
    private Route stockValue(Long id) {
        return get(() -> {
            CompletionStage<Optional<Player>> player = Patterns.ask(brokerActor, new BrokerMessages.GetTotalStockValueMessage(id), timeout)
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

    // #GET - get all stock Data
    private Route getAllStock() {
        return get(() -> {
            CompletionStage<ArrayList<Stock>> stock = Patterns.ask(stockActor, new StockMessages.GetAllStockMessage(), timeout)
                    .thenApply(obj -> (ArrayList<Stock>) obj);
            return onSuccess(() -> stock,
                    performed -> {
                        if (stock!=null)
                            return complete(StatusCodes.OK,performed,Jackson.marshaller());
//                            return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
    }

    // #POST - sellStock
    private Route sellStock() {
        return route(post(() -> entity(Jackson.unmarshaller(Market.class), market -> {
            CompletionStage<BrokerMessages.ActionPerformed> stockSell = Patterns.ask(brokerActor, new BrokerMessages.BuyStockMessage(market, bankActor), timeout)
                    .thenApply(obj -> (BrokerMessages.ActionPerformed) obj);

            return onSuccess(() -> stockSell, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    // #POST - buyStock
    private Route buyStock() {
        return route(post(() -> entity(Jackson.unmarshaller(Market.class), market -> {
            CompletionStage<BrokerMessages.ActionPerformed> stockBuy = Patterns.ask(brokerActor, new BrokerMessages.BuyStockMessage(market, bankActor), timeout)
                    .thenApply(obj -> (BrokerMessages.ActionPerformed) obj);

            return onSuccess(() -> stockBuy, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    //#POST - Create new Player
    private Route postPlayer() {
        return route(post(() -> entity(Jackson.unmarshaller(Player.class), player -> {
            CompletionStage<PlayerMessages.ActionPerformed> playerCreated = Patterns.ask(playerActor, new PlayerMessages.CreatePlayerMessage(player, bankActor), timeout)
                    .thenApply(obj -> (PlayerMessages.ActionPerformed) obj);

            return onSuccess(() -> playerCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    // #POST - Create new stock
    private Route postStock() {
        return route(post(() -> entity(Jackson.unmarshaller(Stock.class), stock -> {
            CompletionStage<StockMessages.ActionPerformed> stockCreated = Patterns.ask(stockActor, new StockMessages.CreateStockMessage(stock), timeout)
                    .thenApply(obj -> (StockMessages.ActionPerformed) obj);

            return onSuccess(() -> stockCreated, performed -> {
                return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
            });
        })));
    }

    // #GET - get a stock Data
    private Route getStock(Long id) {
        return get(() -> {
            CompletionStage<Optional<Stock>> stock = Patterns.ask(stockActor, new StockMessages.GetStockMessage(id), timeout)
                    .thenApply(obj -> (Optional<Stock>) obj);
            return onSuccess(() -> stock,
                    performed -> {
                        if (performed.isPresent())
                            return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
    }

    // #GET - get stocks Data by sector
    private Route getStockBySector(Long sector) {
        return get(() -> {
            CompletionStage<Optional<Stock>> stock = Patterns.ask(stockActor, new StockMessages.GetStockSectorMessage(sector), timeout)
                    .thenApply(obj -> (Optional<Stock>) obj);
            return onSuccess(() -> stock,
                    performed -> {
                        if (performed.isPresent())
                            return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
                        else
                            return complete(StatusCodes.NOT_FOUND);
                    });
        });
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


