package Model;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrokerTest {
	public Logger log;
	Broker broker;

	@BeforeClass
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Broker Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Broker Test");
	}

	@Test
	public void createBroker() {
		try {
			log.debug("Creating New Broker");
			broker = new Broker((long) 25, "Test Broker");
			log.debug("Broker: \"" + broker.getName().toString() + "\" Successfully Created");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Broker Creation Unsuccessful" + e);
			log.debug("End of Create Broker");
		}
	}

	@Test
	public void verifyBrokerName() {
		log.debug("Verifying Broker Name");
		assertEquals(broker.getName().toString(), "Test Broker");
		log.debug("Broker Name Successfully Verified");
	}

	@Test
	public void verifyBrokerId() {
		log.debug("Verifying  Broker ID");
		assertEquals(broker.getId().toString(), "25");
		log.debug("Broker ID Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Broker: \"" + broker.getName() + "\" Created & Verified Successfully with an ID: "
				+ broker.getId().toString());
		log.debug("End of Broker Test");
	}
}
