package com.Controller;

import Model.Player;
import Service.PlayerService;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin
@RestController
public class StockController {
    private PlayerService playerService = new PlayerService();

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to Stock market Simulator API.";
    }

    @RequestMapping(value = "/players/10", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Player players() throws Exception {
        return new Player(1l, "Chamod");
    }

    @RequestMapping(value = "players", method = RequestMethod.POST)
    public Player saveMainCatergory(@ModelAttribute("mainCatergoryForm") Player player, Model model, BindingResult brequest, HttpServletRequest request) {
        playerService.createPlayer(player);
        return new Player(1l, "Chamod");
    }

    }
