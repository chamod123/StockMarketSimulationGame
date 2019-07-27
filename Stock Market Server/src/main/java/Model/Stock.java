package Model;

import java.math.BigDecimal;

public class Stock {
	private int stockId;
	private BigDecimal stockPrice;
	private String companyName;
	private Sector sector;
	private static int stockIdCounter = 0;

	public Stock() {
		this.stockId = 0;
		this.stockPrice = null;
		this.companyName = "";
		this.sector = null;
	}

	public Stock(String companyName, Sector sector, BigDecimal stockPrice) {
		this.stockId = stockIdCounter++;
		this.stockPrice = stockPrice;
		this.companyName = companyName;
		this.sector = sector;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
}
