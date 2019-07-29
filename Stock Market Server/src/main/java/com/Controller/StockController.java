package com.Controller;

import Actors.PlayerActor;
import Messages.BrokerMessages;
import Messages.PlayerMessages;
import Messages.StockMessages;
import Model.Player;
import Model.Stock;
import Model.Market;
import Service.PlayerService;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.pattern.Patterns;
import com.ActorSystemCreate;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@CrossOrigin
@RestController
public class StockController {
    private PlayerService playerService = new PlayerService();
    private ActorSystemCreate actorSystemCreate = new ActorSystemCreate();
    Duration timeout = Duration.ofSeconds(100);

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to Stock market Simulator API.";
    }

    //#POST - Create new Player
    @PostMapping("/players")
    public String addPlayer(@RequestBody Player player) {
        System.out.println("" + player);
        CompletionStage<PlayerMessages.ActionPerformed> playerCreated = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.CreatePlayerMessage(player, actorSystemCreate.getBankActor()), timeout)
                .thenApply(PlayerMessages.ActionPerformed.class::cast);
        if (playerCreated != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //#GET - get a Player Data
    @GetMapping("/players/{id}")
    public CompletionStage<Optional<Player>> getPlayer(@PathVariable("id") Long id) {
        System.out.println("GET");
        CompletionStage<Optional<Player>> maybeUser = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.GetPlayerMessage(id), timeout)
                .thenApply(Optional.class::cast);
        return maybeUser;
    }

    //#POST - Create new stock
    @PostMapping("/stock")
    public String postStock(@RequestBody Stock stock) {
        System.out.println("stock : " + stock);
        CompletionStage<StockMessages.ActionPerformed> stockCreated = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.CreateStockMessage(stock), timeout)
                .thenApply(obj -> (StockMessages.ActionPerformed) obj);

        if (stockCreated != null) {
            return "sucess";
        }
        return "not sucess";

    }

    //#GET - get a stock by id
    @GetMapping("/stock/{id}")
    public CompletionStage<Optional<Stock>> getStock(@PathVariable("id") Long id) {
        System.out.println("GET Stock " + id);
        CompletionStage<Optional<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetStockMessage(id), timeout)
                .thenApply(obj -> (Optional<Stock>) obj);
        return stock;
    }

    //#GET - get a stock by sector
    @GetMapping("/stockBySector/{sector}")
    public CompletionStage<List<Stock>> getStockBySector(@PathVariable("sector") String sector) {
        System.out.println("GET Stock By Sector " + sector);
        CompletionStage<List<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetStockSectorMessage(sector), timeout)
                .thenApply(obj -> (List<Stock>) obj);
        return stock;
    }

    //#GET - get all stock
    @GetMapping("/allStock")
    public CompletionStage<ArrayList<Stock>> getAllStock() {
        System.out.println("GET all Stock By Sector ");
        CompletionStage<ArrayList<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetAllStockMessage(), timeout)
                .thenApply(obj -> (ArrayList<Stock>) obj);
        return stock;
    }

    //#POST - sell stock
    @PostMapping("/sellStock")
    public String sellStock(@RequestBody Market market) {
        System.out.println("market : " + market);
        CompletionStage<BrokerMessages.ActionPerformed> stockSell = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.SellStockMessage(market, actorSystemCreate.getBankActor()), timeout)
                .thenApply(obj -> (BrokerMessages.ActionPerformed) obj);

        if (stockSell != null) {
            return "sucess";
        }
        return "not sucess";

    }

//    @RequestMapping(value = "/players/10", //
//            method = RequestMethod.GET, //
//            produces = {MediaType.APPLICATION_JSON_VALUE, //
//                    MediaType.APPLICATION_XML_VALUE})
//    @ResponseBody
//    public Player players() throws Exception {
//        return new Player(1l, "Chamod");
//    }


//        @RequestMapping("players/{id}")
//        public Player players(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
//         actorSystemCreate.getPlayerActor().tell(  new PlayerMessages.GetPlayerMessage(id),actorSystemCreate.getServer());
//        }

//    @RequestMapping(value = "players", method = RequestMethod.POST)
//    public void saveMainCatergory(@ModelAttribute("mainCatergoryForm") Player player, Model model, BindingResult brequest, HttpServletRequest request) {
////        playerService.createPlayer(player);
//        actorSystemCreate.getPlayerActor().tell( new PlayerMessages.CreatePlayerMessage(player, actorSystemCreate.getBankActor()),actorSystemCreate.getServer());
//    }


}
