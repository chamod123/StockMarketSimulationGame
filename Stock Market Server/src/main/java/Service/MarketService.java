package Service;

import Model.Event;
import Model.Sector;
import Model.Stock;

import java.util.ArrayList;
import java.util.HashMap;

public class MarketService {
    public ArrayList<Stock> stocks;
    public HashMap<Sector, ArrayList<Integer>> sectorTrends;
    public ArrayList<Integer> marketTrends;
    public ArrayList<Event> eventList;
}
