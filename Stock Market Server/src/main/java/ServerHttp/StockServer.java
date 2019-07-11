package ServerHttp;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import static akka.http.javadsl.server.PathMatchers.*;

import java.time.Duration;

public class StockServer extends AllDirectives {
    private static final String RESOURCE_PATH = "Stock Market Server/src/main/resources/pages/";
    private static ActorSystem system = ActorSystem.create("ServerHttp");
    private Duration timeout = Duration.ofSeconds(180);


    private ActorRef server;

    public StockServer(ActorSystem system) {
        server = system.actorOf(ServerActor.props());
    }


    public static void main(String[] args) throws Exception {
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        StockServer app = new StockServer(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", 8080), materializer);
        System.out.println("Server online at http://localhost:8080/");
    }

    protected Route createRoute() {
        return route(pathPrefix("api", () ->
                        testout("awa 1")
                ), pathPrefix("aa", () ->
                        testout("awa 2")
                ), path(segment("order").slash(integerSegment()), id -> // path("create-order", () ->
                        concat(get(() -> complete("Received GET request for order " + id)),
                                put(() -> complete("Received PUT request for order " + id)))
                )
        );
    }

    public Route testout(String aaa) {
        System.out.println(aaa);
        return null;
    }


}