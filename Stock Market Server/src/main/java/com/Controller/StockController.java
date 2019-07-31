package com.Controller;

import Actors.PlayerActor;
import Messages.*;
import Model.Player;
import Model.Stock;
import Model.Market;
import Model.Event;
import Model.Transaction;
import Model.Bank;
import Service.PlayerService;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.pattern.Patterns;
import com.ActorSystemCreate;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
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
        CompletionStage<Optional<Player>> player = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.GetPlayerMessage(id), timeout)
                .thenApply(Optional.class::cast);
        return player;
    }

    //#GET - login User
    @GetMapping("/login/{id}/{password}")
    public CompletionStage<Boolean> loginUser(@PathVariable("id") Long id, @PathVariable("password") String password) {
        CompletionStage<Boolean> maybeUser = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.LoginPlayerMessage(id, password), timeout)
                .thenApply(obj -> (Boolean) obj);
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


    //#GET - get winner
    @GetMapping("/winner")
    public CompletionStage<Optional<Player>> getWinner() {
        CompletionStage<Optional<Player>> winner = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetWinnerMessage(), timeout)
                .thenApply(obj -> (Optional<Player>) obj);
        return winner;
    }

    //#GET - get all players
    @GetMapping("/allPlayers")
    public CompletionStage<HashMap<String, ArrayList<BigDecimal>>> getAllPlayers() {
        CompletionStage<HashMap<String, ArrayList<BigDecimal>>> allPlayer = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetAllPlayerMessage(), timeout)
                .thenApply(obj -> (HashMap<String, ArrayList<BigDecimal>>) obj);
        return allPlayer;
    }

    //#POST - start game
    @PostMapping("/start")
    public String StartGame() {
        CompletionStage<AnalystMessages.ActionPerformed> startGame = Patterns.ask(actorSystemCreate.getAnalystActor(), new AnalystMessages.StartGameMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (AnalystMessages.ActionPerformed) obj);
        if (startGame != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //#POST - next turn
    @PostMapping("/nextTurn")
    public String NextTurn() {
        CompletionStage<AnalystMessages.ActionPerformed> startGame = Patterns.ask(actorSystemCreate.getAnalystActor(), new AnalystMessages.NextTurnMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (AnalystMessages.ActionPerformed) obj);
        if (startGame != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //#GET - get prediction
    @GetMapping("/prediction")
    public CompletionStage<ArrayList<String>> getPrediction() {
        CompletionStage<ArrayList<String>> prediction = Patterns.ask(actorSystemCreate.getAnalystActor(), new AnalystMessages.GetPredictionMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (ArrayList<String>) obj);
        return prediction;
    }

    //#GET - get Current Turn
    @GetMapping("/currentTurn")
    public CompletionStage<Integer> currentTurn() {
        CompletionStage<Integer> turn = Patterns.ask(actorSystemCreate.getStockActor(), new AnalystMessages.GetCurrentTurnMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (Integer) obj);
        return turn;
    }

    //go to next turn after 45 seconds
    @Scheduled(fixedDelay = 45000)
    public String nextTurnS() throws Exception {
        CompletionStage<AnalystMessages.ActionPerformed> nextTurn = Patterns.ask(actorSystemCreate.getAnalystActor(), new AnalystMessages.NextTurnMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (AnalystMessages.ActionPerformed) obj);
        if (nextTurn != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //get Current Event
    @GetMapping("/currentEvents")
    public CompletionStage<ArrayList<Event>> currentEvents() throws Exception {
        CompletionStage<ArrayList<Event>> event = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetCurrentEventMessage(), timeout)
                .thenApply(obj -> (ArrayList<Event>) obj);
        return event;

    }
    
    //#POST - add player to game
    @PostMapping("/addPlayer/{id}")
    public String addPlayerToGame(@PathVariable("id") Long id) {
        CompletionStage<GameMessage.ActionPerformed> playerCreated = Patterns
                .ask(actorSystemCreate.getGameActor(), new GameMessage.AddPlayerToGameMessage(id,actorSystemCreate.getPlayerActor(),actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(GameMessage.ActionPerformed.class::cast);
        if (playerCreated != null) {
            return "sucess";
        }
        return "not sucess";
    }
}
