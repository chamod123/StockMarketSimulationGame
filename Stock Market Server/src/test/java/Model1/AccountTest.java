package Old.Model1;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class AccountTest {

	public Logger log;
	Account account;

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
			log.debug("Creating New Account");
			account = new Account("Test Account");
			log.debug("Account: \"" + account.getName().toString() + "\" Successfully Created");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Account Creation Unsuccessful" + e);
			log.debug("End of Create Account");
		}
	}

	@Test
	public void setAccountName() {
		log.debug("Updating the Account Name to \"Test Account Revised\"");
		account.setName("Test Account Revised");
		log.debug("Account Name Successfully Updated");
	}

	@Test
	public void verifyUpdatedAccountName() {
		log.debug("Verifying Account Name");
		assertEquals(account.getName().toString(), "Test Account Revised");
		log.debug("Account Name Successfully Verified");
	}

	@Test
	public void setBalance() {
		log.debug("Setting Account Balance to \"1000\"");
		account.setBalance(BigDecimal.valueOf(1000));
		log.debug("Account Balance Successfully Set");
	}

	@Test
	public void verifyBalance() {
		log.debug("Verifying Account Balance");
		assertEquals(account.getBalance().toString(), "1000");
		log.debug("Account Balance Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Account: \"" + account.getName() + "\" Created & Verified Successfully with a Balance: "
				+ account.getBalance().toString());
		log.debug("End of Account Test");
	}
}
