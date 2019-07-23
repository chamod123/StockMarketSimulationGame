package Model;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlayerTest {

	public Logger log;

	@BeforeClass
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Player Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Player Test");
	}

	@Test
	public void createPlayer() {
		try {
			log.debug("Start of Create Player");
			Player player = new Player((long) 25, "Deelaka");
			assertEquals(player.getName(), "Deelaka");
			assertEquals(player.getId().toString(), "25");
			log.debug("Player Created Successfully with the Name: "+player.getName() + " and ID: "+player.getId().toString());
			log.debug("End of Create Player");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Player Creation Unsuccessful" + e);
			log.debug("End of Create Player");
		}
	}

	@AfterClass
	public void afterClass() {
		log.debug("End of Player Test");
	}
}
