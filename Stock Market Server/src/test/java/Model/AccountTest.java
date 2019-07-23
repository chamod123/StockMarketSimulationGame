package Model;

import static org.testng.Assert.assertEquals;

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
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Account Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Account Test");
	}

	@Test
	public void createAccount() {
		try {
			log.debug("Start of Create Account");
			Account account = new Account((long) 20);
			account.setBalance(BigDecimal.valueOf(1000));
			assertEquals(account.getBalance().toString(), "1000");
			log.debug("Account Created Successfully with a Balance: "+account.getBalance().toString());
			log.debug("End of Create Account");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Account Creation Unsuccessful" + e);
			log.debug("End of Create Account");
		}
	}
	
	@AfterClass
	public void afterClass() {
		log.debug("End of Account Test");
	}
}
