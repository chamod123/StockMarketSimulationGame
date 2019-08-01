package Old.Model1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Event {

    EventType eventType;
    Stock stock=null;
    Sector sector=null;
    int value;
    String type;
    int duration;
    int startTurn;
    int endTurn;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStartTurn() {
        return startTurn;
    }

    public void setStartTurn(int startTurn) {
        this.startTurn = startTurn;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public int getEndTurn() {
        return endTurn;
    }

    public void setEndTurn(int endTurn) {
        this.endTurn = endTurn;
    }




    public Event(ArrayList<Stock> stocks, int startTurn) {
        this.startTurn = startTurn;
        this.endTurn = endTurn;
        double p = Math.random();
        double cumulativeProbability = 0.0;
        ArrayList<EventType> list;
        Map<String, Double> types = new HashMap<String, Double>() {{
            put("SectorEvent", 0.33);
            put("StockEvent", 0.67);
        }};

        //get the event type sectorEvent or Stock Event
        for (String key : types.keySet()) {
            cumulativeProbability += types.get(key);
            if (p <= cumulativeProbability) {
                type = key;
                cumulativeProbability = 0.0;
            }
        }
        //set The sub event type
        if (type.equals("SectorEvent")) {
            list = new ArrayList<EventType>() {{
                add(EventType.BOOM);
                add(EventType.BUST);

            }};
            //select a event type using probability
            for (EventType et : list) {
                cumulativeProbability += et.getProbability();
                if (p <= cumulativeProbability) {
                    eventType = et;
                    cumulativeProbability = 0.0;
                    ;
                    break;
                }
            }
            //select a random sector to apply the event
            sector = Sector.getRandomSector();
            //set the duration(in turns)
            Random random = new Random();
            duration = random.nextInt(5 - 2 + 1) + 2;
            //get value of the event
            value = random.nextInt(eventType.getMax() + 1 - eventType.getMin()) + eventType.getMin();

        }
        if (type.equals("StockEvent")) {
            list = new ArrayList<EventType>() {{
                add(EventType.PROFIT_WARNING);
                add(EventType.TAKE_OVER);
                add(EventType.SCANDAL);

            }};
            //select a event type using probability
            for (EventType et : list) {
                cumulativeProbability += et.getProbability();
                if (p <= cumulativeProbability) {
                    eventType = et;
                    cumulativeProbability = 0.0;
                    break;
                }
            }
            //select a random stock to apply the event
            stock = stocks.get((new Random()).nextInt(stocks.size()));
            //set duration in turns
            Random random = new Random();
            duration = random.nextInt(7 - 1 + 1) + 1;
            //get value of the event
            //get value of the event
            value = random.nextInt(eventType.getMax() + 1 - eventType.getMin()) + eventType.getMin();
        }

        this.endTurn = this.startTurn + duration;


    }
}
