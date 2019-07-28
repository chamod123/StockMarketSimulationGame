package com.Controller;

import Actors.PlayerActor;
import Messages.PlayerMessages;
import Model.Player;
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

    @GetMapping("/players/{id}")
    public CompletionStage<Optional<Player>> getPlayer(@PathVariable("id") Long id) {
        System.out.println("Post");
        CompletionStage<Optional<Player>> maybeUser = Patterns
                .ask(actorSystemCreate.getPlayerActor(), new PlayerMessages.GetPlayerMessage(id), timeout)
                .thenApply(Optional.class::cast);
        return maybeUser;
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
