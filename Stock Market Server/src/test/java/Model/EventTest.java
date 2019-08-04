package Model;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EventTest {

	public Logger log;
	Event event;
	Stock stock;
	private Sector sector;
	ArrayList<Stock> testStock;

	@BeforeClass
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Event Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Event Test");
		testStock = new ArrayList<Stock>();
	}

	@Test
	public void createEvent() {
		try {
			log.debug("Creating New Event");
			stock = new Stock("Test Company", sector, BigDecimal.valueOf(250));
			testStock.add(stock);
			event = new Event(testStock, 5);
			log.debug("Event Created Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Event Creation Unsuccessful" + e);
		}
	}

	@Test
	public void updateEventValue() {
		log.debug("Updating Event Value: \"" + event.getValue() + "\" to \"100\"");
		event.setValue(100);
		log.debug("Event Value Successfully Updated");
	}

	@Test
	public void verifyUpdatedEventValue() {
		log.debug("Verifying Event Value");
		assertEquals(event.getValue(), 100);
		log.debug("Event Value Successfully Verified");
	}

	@Test
	public void updateEventType() {
		log.debug("Updating Event Type.");
		event.setType("Test Event Type");
		log.debug("Event Value Successfully Updated");
	}

	@Test
	public void verifyUpdatedEventType() {
		log.debug("Verifying Event Value");
		assertEquals(event.getType().toString(), "Test Event Type");
		log.debug("Event Value Successfully Verified");
	}

	@Test
	public void updateEventDuration() {
		log.debug("Updating Event Duration.");
		event.setDuration(60);
		log.debug("Event Duration Successfully Updated");
	}

	@Test
	public void verifyUpdatedEventDuration() {
		log.debug("Verifying Event Duration");
		assertEquals(event.getDuration(), 60);
		log.debug("Event Duration Successfully Verified");
	}

	@Test
	public void updateEventStartTurn() {
		log.debug("Updating Event Start Turn.");
		event.setStartTurn(05);
		log.debug("Event Start Turn Successfully Updated");
	}

	@Test
	public void verifyUpdatedEventStartTurn() {
		log.debug("Verifying Event Start Turn");
		assertEquals(event.getStartTurn(), 05);
		log.debug("Event Start Turn Successfully Verified");
	}

	@Test
	public void updateEventEndTurn() {
		log.debug("Updating Event End Turn.");
		event.setEndTurn(60);
		log.debug("Event End Turn Successfully Updated");
	}

	@Test
	public void verifyUpdatedEventEndTurn() {
		log.debug("Verifying Event End Turn");
		assertEquals(event.getEndTurn(), 60);
		log.debug("Event End Turn Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Event Created & Verified Successfully.");
		log.debug("End of Event Test");
	}

}
