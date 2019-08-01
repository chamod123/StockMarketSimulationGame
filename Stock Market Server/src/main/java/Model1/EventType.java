package Old.Model1;

public enum EventType {

    BOOM(0.5,+1,+5),
    BUST(0.5,-5,-1),
    PROFIT_WARNING(0.5,+2,+3),
    TAKE_OVER(0.25,-5,-1),
    SCANDAL(0.25,-6,-3);


    private final Double prob;
    private final int min;
    private final int max;

    private EventType(Double prob,int min,int max) {
        this.prob=prob;
        this.min=min;
        this.max=max;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public Double getProbability() {
        return prob;
    }
}
