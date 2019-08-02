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
    private ActorSystemCreate actorSystemCreate = new ActorSystemCreate();
    Duration timeout = Duration.ofSeconds(100);

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to Stock market Simulator API.";
    }

    //checked
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

    //checked
    //#GET - get a Players Data
    @GetMapping("/players/{id}")
    public CompletionStage<Player> getPlayer(@PathVariable("id") Long id) {
        CompletionStage<Player> player = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.GetPlayerMessage(id), timeout)
                .thenApply(Player.class::cast);
        return player;
    }

    //checked
    //#POST - update Player
    @PostMapping("/updateplayers")
    public String updatePlayer(@RequestBody Player player) {
        CompletionStage<PlayerMessages.ActionPerformed> playerCreated = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.UpdatePlayerMessage(player), timeout)
                .thenApply(PlayerMessages.ActionPerformed.class::cast);
        if (playerCreated != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //checked
    //#GET - get all Player Data
    @GetMapping("/players")
    public CompletionStage<ArrayList<Player>> getallPlayers() {
        CompletionStage<ArrayList<Player>> player = Patterns
                .ask(actorSystemCreate.getGameActor(), new GameMessage.GetallPlayerMessage(actorSystemCreate.getBrokerActor()), timeout)
                 .thenApply(obj -> (ArrayList<Player>) obj);
        return player;
    }

    //checked
    //#GET - login User
    @GetMapping("/login/{userName}/{password}")
    public CompletionStage<Integer> loginUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        CompletionStage<Integer> maybeUser = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.LoginPlayerMessage(userName, password), timeout)
                .thenApply(obj -> (Integer) obj);
        return maybeUser;
    }

    //checked
    //#POST - add player to game / start game
    @PostMapping("/addPlayer/{id}")
    public CompletionStage<GameMessage.ActionPerformed> addPlayerToGame(@PathVariable("id") Long id) {
        CompletionStage<GameMessage.ActionPerformed> playerCreated = Patterns
                .ask(actorSystemCreate.getGameActor(), new GameMessage.AddPlayerToGameMessage(id, actorSystemCreate.getPlayerActor(), actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(GameMessage.ActionPerformed.class::cast);
        if (playerCreated != null) {
            return playerCreated;
        }
        return playerCreated;
    }


    //checked
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

    //checked
    //#POST - update stock
    @PostMapping("/updatestock")
    public String updateStock(@RequestBody Stock stock) {
        CompletionStage<StockMessages.ActionPerformed> stockCreated = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.UpdateStockMessage(stock), timeout)
                .thenApply(obj -> (StockMessages.ActionPerformed) obj);
        if (stockCreated != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //checked
    //#GET - get a stock by id
    @GetMapping("/stock/{id}")
    public CompletionStage<Optional<Stock>> getStock(@PathVariable("id") Long id) {
        CompletionStage<Optional<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetStockMessage(id), timeout)
                .thenApply(obj -> (Optional<Stock>) obj);
        return stock;
    }

    //checked
    //#GET - get a stock by sector
    @GetMapping("/stockBySector/{sector}")
    public CompletionStage<List<Stock>> getStockBySector(@PathVariable("sector") String sector) {
        CompletionStage<List<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetStockSectorMessage(sector), timeout)
                .thenApply(obj -> (List<Stock>) obj);
        return stock;
    }

    //checked
    //#GET - get all stock
    @GetMapping("/allStock")
    public CompletionStage<ArrayList<Stock>> getAllStock() {
        CompletionStage<ArrayList<Stock>> stock = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetAllStockMessage(), timeout)
                .thenApply(obj -> (ArrayList<Stock>) obj);
        return stock;
    }


    //checked
    //#GET - get Current Turn
    @GetMapping("/currentTurn")
    public CompletionStage<Integer> currentTurn() {
//        CompletionStage<Integer> turn = Patterns.ask(actorSystemCreate.getAnalystActor(), new AnalystMessages.GetCurrentTurnMessage(actorSystemCreate.getBrokerActor()), timeout)
//                .thenApply(obj -> (Integer) obj);

        CompletionStage<Integer> turn = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetCurrentTurnMessage(), timeout)
                .thenApply(obj -> (Integer) obj);
        return turn;
    }


    //checked
    //get Current Event
    @GetMapping("/currentEvents")
    public CompletionStage<ArrayList<Event>> currentEvents() throws Exception {
        CompletionStage<ArrayList<Event>> event = Patterns.ask(actorSystemCreate.getStockActor(), new StockMessages.GetCurrentEventMessage(), timeout)
                .thenApply(obj -> (ArrayList<Event>) obj);
        return event;

    }

    //checked
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

    //checked
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

    //checked
    // #GET - get total stock value for player
    //if sell all items they have. how much they can earn
    @GetMapping("/stockValue/{name}")
    public CompletionStage<BigDecimal> stockValue(@PathVariable("name") String name) {
        CompletionStage<BigDecimal> player = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetTotalStockValueMessage(name), timeout)
                .thenApply(obj -> (BigDecimal) obj);
        return player;
    }

    //checked
    // #GET - get getPortofolio
    // stockes player have
    @GetMapping("/portofolio/{name}")
    public CompletionStage<HashMap<String, Integer>> getPortofolio(@PathVariable("name") String name) {
        CompletionStage<HashMap<String, Integer>> player = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetPortofolioMessage(name), timeout)
                .thenApply(obj -> (HashMap<String, Integer>) obj);
        return player;
    }

    //checked
    // #GET - get Bank Balance for a player
    @GetMapping("/bankBalance/{name}")
    public CompletionStage<BigDecimal> getBankBalance(@PathVariable("name") String name) {
        CompletionStage<BigDecimal> balance = Patterns.ask(actorSystemCreate.getBankActor(), new BankMessages.GetBankBalanceMessage(name), timeout)
                .thenApply(obj -> (BigDecimal) obj);
        return balance;
    }


    //checked
    //#GET - get all transactions
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

//    //#POST - start game
//    @PostMapping("/start")
//    public String StartGame() {
//        CompletionStage<AnalystMessages.ActionPerformed> startGame = Patterns.ask(actorSystemCreate.getAnalystActor(), new AnalystMessages.StartGameMessage(actorSystemCreate.getBrokerActor()), timeout)
//                .thenApply(obj -> (AnalystMessages.ActionPerformed) obj);
//        if (startGame != null) {
//            return "sucess";
//        }
//        return "not sucess";
//    }

    //checked
    //#POST - next turn
    @PostMapping("/nextTurn")
    public String NextTurn() {
        CompletionStage<ClockMessages.ActionPerformed> nextTurn = Patterns.ask(actorSystemCreate.getClockActor(), new ClockMessages.NextTurnMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (ClockMessages.ActionPerformed) obj);
        if (nextTurn != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //checked
    //go to next turn after 45 seconds
    @Scheduled(fixedDelay = 45000)
    public String nextTurnS() throws Exception {
        CompletionStage<ClockMessages.ActionPerformed> nextTurn = Patterns.ask(actorSystemCreate.getClockActor(), new ClockMessages.NextTurnMessage(actorSystemCreate.getBrokerActor()), timeout)
                .thenApply(obj -> (ClockMessages.ActionPerformed) obj);
        if (nextTurn != null) {
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

    //#GET - get all stock for graph
    @GetMapping("/graph")
    public CompletionStage<ArrayList<String[]>> getAllStocksGrapgh() {
        CompletionStage<ArrayList<String[]>> stock = Patterns.ask(actorSystemCreate.getBrokerActor(), new BrokerMessages.GetAllStocksMessage(), timeout)
                .thenApply(obj -> (ArrayList<String[]>) obj);
        return stock;
    }

    //checked
    //#POST - withdraw
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Transaction transaction) {//name and amount
        CompletionStage<PlayerMessages.ActionPerformed> stockBuy = Patterns.ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.WithdrawMessage(transaction, actorSystemCreate.getBankActor()), timeout)
                .thenApply(obj -> (PlayerMessages.ActionPerformed) obj);
        if (stockBuy != null) {
            return "sucess";
        }
        return "not sucess";
    }

    //checked
    //#POST - deposit
    @PostMapping("/deposit")
    public String deposit(@RequestBody Transaction transaction) {//name and amount
        CompletionStage<PlayerMessages.ActionPerformed> stockBuy = Patterns.ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.DepoditMessage(transaction, actorSystemCreate.getBankActor()), timeout)
                .thenApply(obj -> (PlayerMessages.ActionPerformed) obj);
        if (stockBuy != null) {
            return "sucess";
        }
        return "not sucess";
    }

}
