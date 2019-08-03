package Model;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarketTest {

	public Logger log;
	Market market;

	@BeforeClass
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Market Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Market Test");
	}

	@Test
	public void createMarket() {
		try {
			log.debug("Creating New Market");
			market = new Market("Test User", "Test Stock", 2);
			log.debug("Market Created Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Market Creation Unsuccessful" + e);
		}
	}

	@Test
	public void updateMarketName() {
		log.debug("Updating Market User Name: \"" + market.getUsername() + "\" to \"Test User Revised\"");
		market.setUsername("Test User Revised");
		log.debug("Market User Name Successfully Updated");
	}

	@Test
	public void verifyUpdatedMarketName() {
		log.debug("Verifying Market Name");
		assertEquals(market.getUsername().toString(), "Test User Revised");
		log.debug("Market Name Successfully Verified");
	}

	@Test
	public void updateMarketStock() {
		log.debug("Updating Market Stock: \"" + market.getStock() + "\" to \"20\"");
		market.setStock("Test Revised Stock");
		log.debug("Market Stock Successfully Updated");
	}

	@Test
	public void verifyUpdatedPlayerId() {
		log.debug("Verifying Market Stock");
		assertEquals(market.getStock(), "Test Revised Stock");
		log.debug("Market Stock Successfully Verified");
	}

	@Test
	public void updateMarketQuantity() {
		log.debug("Updating Market Quantity: \"" + market.getQuantity() + "\" to \"20\"");
		market.setQuantity(200);
		log.debug("Market Quantity Successfully Updated");
	}

	@Test
	public void verifyUpdatedQuantity() {
		log.debug("Verifying Market Quantity");
		assertEquals(market.getQuantity(), 200);
		log.debug("Market Quantity Successfully Verified");
	}

	@Test
	public void updateMarketTurns() {
		log.debug("Updating Market Turns: \"" + market.getTurns() + "\" to \"20\"");
		market.setTurns(20);
		log.debug("Market Turns Successfully Updated");
	}

	@Test
	public void verifyUpdatedTurns() {
		log.debug("Verifying Market Turns");
		assertEquals(market.getTurns(), 20);
		log.debug("Market Turns Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Market Created & Verified Successfully.");
		log.debug("End of Market Test");
	}
}
