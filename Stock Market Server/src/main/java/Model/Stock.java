package Model;


public class Stock {
    private Long stockId;
    private double stockPrice;
    private String companyName;
    private Long sector;
//    private static int stockIdCounter = 0;

    public Stock(){
        this.stockId= null;
        this.stockPrice=0.00;
        this.companyName="";
        this.sector=null;
    }

    public Stock(Long stockId, double stockPrice, String companyName, Long sector){
        this.stockId=stockId;
        this.stockPrice=stockPrice;
        this.companyName=companyName;
        this.sector = sector;
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

    public Long getSector() {
        return sector;
    }

    public void setSector(Long sector) {
        this.sector = sector;
    }
}
