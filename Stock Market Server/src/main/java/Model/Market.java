package Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Market {

    public ArrayList<Stock> stocks;
    public ArrayList<Integer> marketTrends;
    public ArrayList<Event> eventList;
    public int turns=20;
    private int currentTurn=0;

}
