package com.Controller;

import Actors.PlayerActor;
import Messages.BankMessages;
import Messages.BrokerMessages;
import Messages.PlayerMessages;
import Messages.StockMessages;
import Model.Player;
import Model.Stock;
import Model.Market;
import Model.Transaction;
import Model.Bank;
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
        CompletionStage<Optional<Player>> maybeUser = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.GetPlayerMessage(id), timeout)
                .thenApply(Optional.class::cast);
        return maybeUser;
    }

    //#POST - Create new stock
    @PostMapping("/stock")
    public String postStock(@RequestBody Stock stock) {
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
        CompletionStage<Optional<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetStockMessage(id), timeout)
                .thenApply(obj -> (Optional<Stock>) obj);
        return stock;
    }

    //#GET - get a stock by sector
    @GetMapping("/stockBySector/{sector}")
    public CompletionStage<List<Stock>> getStockBySector(@PathVariable("sector") String sector) {
        CompletionStage<List<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetStockSectorMessage(sector), timeout)
                .thenApply(obj -> (List<Stock>) obj);
        return stock;
    }

    //#GET - get all stock
    @GetMapping("/allStock")
    public CompletionStage<ArrayList<Stock>> getAllStock() {
        CompletionStage<ArrayList<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetAllStockMessage(), timeout)
                .thenApply(obj -> (ArrayList<Stock>) obj);
        return stock;
    }

    //#POST - sell stock
    @PostMapping("/sellStock")
    public String sellStock(@RequestBody Market market) {
        CompletionStage<BrokerMessages.ActionPerformed> stockSell = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.SellStockMessage(market, actorSystemCreate.getBankActor()), timeout)
                .thenApply(obj -> (BrokerMessages.ActionPerformed) obj);

        if (stockSell != null) {
            return "sucess";
        }
        return "not sucess";

    }

    //#POST - buy stock
    @PostMapping("/buyStock")
    public String buyStock(@RequestBody Market market) {
        CompletionStage<BrokerMessages.ActionPerformed> stockBuy = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.BuyStockMessage(market, actorSystemCreate.getBankActor()), timeout)
                .thenApply(obj -> (BrokerMessages.ActionPerformed) obj);

        if (stockBuy != null) {
            return "sucess";
        }
        return "not sucess";

    }

    // #GET - get total stock value for player
    @GetMapping("/stockValue")
    public CompletionStage<Optional<Player>> stockValue(@PathVariable("name") String name) {
        CompletionStage<Optional<Player>> player = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetTotalStockValueMessage(name), timeout)
                .thenApply(obj -> (Optional<Player>) obj);
        return player;
    }

    // #GET - get getPortofolio
    @GetMapping("/portofolio/{name}")
    public CompletionStage<HashMap<String, Integer>> getPortofolio(@PathVariable("name") String name) {
        CompletionStage<HashMap<String, Integer>> player = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetPortofolioMessage(name), timeout)
                .thenApply(obj -> (HashMap<String, Integer>) obj);
        return player;
    }

    // #GET - get Bank Balance for a player
    @GetMapping("/bankBalance/{name}")
    public CompletionStage<BigDecimal> getBankBalance(@PathVariable("name") String name) {
        CompletionStage<BigDecimal> balance = Patterns.ask(actorSystemCreate.getBankActor(), new BankMessages.GetBankBalanceMessage(name), timeout)
                .thenApply(obj -> (BigDecimal) obj);
        return balance;
    }


    //#GET - get all stock
    @GetMapping("/transactions")
    public CompletionStage<ArrayList<Transaction>> getAllTransactions() {
        CompletionStage<ArrayList<Transaction>> transaction = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetAllTransactionsMessage(), timeout)
                .thenApply(obj -> (ArrayList<Transaction>) obj);
        return transaction;
    }





}
