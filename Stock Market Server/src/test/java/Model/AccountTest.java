package Model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import Model.Account;

public class AccountTest {

	public Logger log;

	@BeforeClass
	public void beforeMethod() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
	}

	@Test
	public void account() {
		try {
			log.debug("Start of New Account Test");
			Account account = new Account("Deelaka");
			account.setBalance(BigDecimal.valueOf(1000));
			log.debug("Account Created Successfully");
			log.debug("End of New Account Test");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Account Creation Test Unsuccessful" + e);
		}
	}
}
