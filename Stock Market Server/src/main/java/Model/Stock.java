package Model;


public class Stock {
    private Long stockId;
    private double stockPrice;
    private String companyName;
//    private static int stockIdCounter = 0;

    public Stock(){
        this.stockId= null;
        this.stockPrice=0.00;
        this.companyName="";

    }

    public Stock(Long stockId, double stockPrice, String companyName){
        this.stockId=stockId;
        this.stockPrice=stockPrice;
        this.companyName=companyName;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
