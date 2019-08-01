package Old.Model1;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlayerTest {

	public Logger log;
	Player player;

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
			log.debug("Creating New Player");
			player = new Player( 25, "Test User");
			log.debug("Player Created Successfully with the Name: " + player.getName() + " and ID: "
					+ player.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Player Creation Unsuccessful" + e);
		}
	}

	@Test
	public void updatePlayerName() {
		log.debug("Updating Player Name: \"" + player.getName() + "\" to \"Test User Revised\"");
		player.setName("Test User Revised");
		log.debug("Player Name Successfully Updated");
	}

	@Test
	public void verifyUpdatedPlayerName() {
		log.debug("Verifying PLayer Name");
		assertEquals(player.getName().toString(), "Test User Revised");
		log.debug("Player Name Successfully Verified");
	}

	@Test
	public void updatePlayerId() {
		log.debug("Updating Player ID: \"" + player.getId() + "\" to \"20\"");
		player.setId(20);
		log.debug("Player ID Successfully Updated");
	}

	@Test
	public void verifyUpdatedPlayerId() {
		log.debug("Verifying PLayer ID");
		assertEquals(player.getId(), "20");
		log.debug("Player ID Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Player: \"" + player.getName() + "\" Created & Verified Successfully with an ID: "
				+ player.getId());
		log.debug("End of Player Test");
	}
}
