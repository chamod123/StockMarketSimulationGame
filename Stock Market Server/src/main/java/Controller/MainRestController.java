package Controller;
import Model.Player;
import Model.Stock;
import Service.BrokerService;
import Service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;


@CrossOrigin
@RestController
public class MainRestController {

    @Autowired
    private BrokerService brokerServer;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to Stock market Simulator API.";
    }

    //start a game
    // http://localhost:8080/start/
    @RequestMapping(value = "/start", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Player CreateAccount(@RequestParam(value="name", required=true) String name)throws Exception {
        System.out.println("(Service Side) Game Started : " + name);
        return  brokerServer.CreateAccount(name);

    }

    //Buy Stock
    // http://localhost:8080/buyStock/
    @RequestMapping(value = "/buyStock", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public boolean buyStock(@RequestParam(value = "name",required = true) String name, @RequestParam(value="stock", required=true) String stock,
                            @RequestParam(value="quantity", required=true) int quantity, @RequestParam(value = "totalvalue",required = true)BigDecimal totalvalue)throws Exception {
        System.out.println("(Service Side)"+ name+" bought "+quantity+" "+stock+" stocks");
        return brokerServer.buyStock(name, stock, quantity,totalvalue);

    }


    //Sell Stock
    // http://localhost:8080/SomeContextPath/game/sellStock/{username}
    @RequestMapping(value = "/sellStock", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public boolean sellStock(@RequestParam("username") String username,@RequestParam(value="stock", required=true) String stock,
                             @RequestParam(value="quantity", required=true) int quantity, @RequestParam(value = "totalvalue",required = true)BigDecimal totalvalue)throws Exception {
        System.out.println("(Service Side)"+ username+" sold "+quantity+" "+stock+" stocks");
        return brokerServer.selltock(username, stock, quantity,totalvalue);

    }


    //get player Portofolio
    // http://localhost:8080/portofolio/
    @RequestMapping(value = "portofolio/", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Player getportofolio(@RequestParam("name") String name)throws Exception {
        return brokerServer.GetPlayer(name);

    }


    @Autowired
    private MarketService marketService;

    //get all Stocks
    // http://localhost:8080/stocks/
    @RequestMapping(value = "/stocks", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ArrayList<Stock> getStocks()throws Exception {

        return marketService.getStocks();

    }

    //get player total stock value
    // http://localhost:8080/stockValue
    @RequestMapping(value = "/stockValue", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public  BigDecimal stockValue(@PathVariable("name") String name)throws Exception {

        return brokerServer.GetTotalStockValue(name);

    }






}
