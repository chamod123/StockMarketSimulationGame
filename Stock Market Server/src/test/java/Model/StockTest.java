package Model;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StockTest {
	public Logger log;
	Stock stock;
	private Sector sector;

	@BeforeClass
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Stock Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Stock Test");
	}

	@Test
	public void createStock() {
		try {
			log.debug("Creating New Player");
			stock = new Stock("Test Company", sector, BigDecimal.valueOf(250));
			log.debug("Stock Created Successfully with the ID: " + stock.getStockId());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Stock Creation Unsuccessful" + e);
		}
	}

	@Test
	public void updateStockID() {
		log.debug("Updating Stock ID: \"" + stock.getStockId() + "\" to \"10\"");
		stock.setStockId(10);
		log.debug("Stock ID Successfully Updated");
	}

	@Test
	public void verifyUpdatedStockId() {
		log.debug("Verifying Updated Stock ID");
		assertEquals(stock.getStockId(), 10);
		log.debug("Stock ID Successfully Verified");
	}

	@Test
	public void updateStockPrice() {
		log.debug("Updating Stock Price: \"" + stock.getStockPrice().toString() + "\" to \"500\"");
		stock.setStockPrice(BigDecimal.valueOf(500));
		log.debug("Stock Price Successfully Updated");
	}

	@Test
	public void verifyUpdatedStockPrice() {
		log.debug("Verifying Updated Stock Price");
		assertEquals(stock.getStockPrice().toString(), "500");
		log.debug("Stock Price Successfully Verified");
	}

	@Test
	public void updateStockCompany() {
		log.debug("Updating Stock Company: \"" + stock.getCompanyName().toString() + "\" to \"Apple\"");
		stock.setCompanyName("Apple");
		log.debug("Stock Company Successfully Updated");
	}

	@Test
	public void verifyUpdatedStockCompany() {
		log.debug("Verifying Updated Stock Company");
		assertEquals(stock.getCompanyName().toString(), "Apple");
		log.debug("Stock Company Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Stock: \"" + stock.getStockId() + "\" Created & Verified Successfully.");
		log.debug("End of Stock Test");
	}
}
