package Model;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TransactionTest {

	public Logger log;
	Transaction transaction;

	@BeforeClass
	public void beforeClass() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("currenttime", dateFormat.format(new Date()));
		System.setProperty("projectroot", System.getProperty("user.dir") + "\\TestExecutionLogs");
		System.setProperty("filename", "Transaction Test");
		log = Logger.getLogger("TestStep");
		log.debug("Stock Market Server - Testing");
		log.debug("Start of Transaction Test");
	}

	@Test
	public void createTransaction() {
		try {
			log.debug("Creating New Transaction");
			transaction = new Transaction("Test Transaction", 2, "Test Type", "Test Stock", 200,
					BigDecimal.valueOf(20));
			log.debug("Transaction Created Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Transaction Creation Unsuccessful" + e);
		}
	}

	@Test
	public void updateTransactionName() {
		log.debug("Updating Transaction Name: \"" + transaction.getName() + "\" to \"Test Transaction Revised\"");
		transaction.setName("Test Transaction Revised");
		log.debug("Transaction Name Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionName() {
		log.debug("Verifying Transaction Name");
		assertEquals(transaction.getName().toString(), "Test Transaction Revised");
		log.debug("Transaction Name Successfully Verified");
	}

	@Test
	public void updateTransactionAmount() {
		log.debug("Updating Transaction Amount: \"" + transaction.getAmount() + "\" to \"250\"");
		transaction.setAmount(BigDecimal.valueOf(250));
		log.debug("Transaction Amount Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionAmount() {
		log.debug("Verifying Transaction Amount");
		assertEquals(transaction.getAmount().toString(), "250");
		log.debug("Transaction Amount Successfully Verified");
	}

	@Test
	public void updateTransactionNumber() {
		log.debug("Updating Transaction Number.");
		Transaction.setNumber(50);
		log.debug("Transaction Number Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionNumber() {
		log.debug("Verifying Transaction Number");
		assertEquals(Transaction.getNumber(), 50);
		log.debug("Transaction Number Successfully Verified");
	}

	@Test
	public void updateTransactionId() {
		log.debug("Updating Transaction ID.");
		transaction.setId(99);
		log.debug("Transaction ID Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionId() {
		log.debug("Verifying Transaction ID");
		assertEquals(transaction.getId(), 99);
		log.debug("Transaction ID Successfully Verified");
	}

	@Test
	public void updateTransactionTurn() {
		log.debug("Updating Transaction Turn.");
		transaction.setTurn(78);
		log.debug("Transaction Turn Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionTurn() {
		log.debug("Verifying Transaction Turn");
		assertEquals(transaction.getTurn(), 78);
		log.debug("Transaction Turn Successfully Verified");
	}

	@Test
	public void updateTransactionType() {
		log.debug("Updating Transaction Type: \"" + transaction.getName() + "\" to \"Test Transaction Type Revised\"");
		transaction.setType("Test Transaction Type Revised");
		log.debug("Transaction Type Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionType() {
		log.debug("Verifying Transaction Type");
		assertEquals(transaction.getType().toString(), "Test Transaction Type Revised");
		log.debug("Transaction Type Successfully Verified");
	}

	@Test
	public void updateTransactionStock() {
		log.debug(
				"Updating Transaction Stock: \"" + transaction.getName() + "\" to \"Test Transaction Stock Revised\"");
		transaction.setStock("Test Transaction Stock Revised");
		log.debug("Transaction Stock Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionStock() {
		log.debug("Verifying Transaction Stock");
		assertEquals(transaction.getStock().toString(), "Test Transaction Stock Revised");
		log.debug("Transaction Stock Successfully Verified");
	}

	@Test
	public void updateTransactionQuantity() {
		log.debug("Updating Transaction Quantity.");
		transaction.setQuantity(100);
		log.debug("Transaction Quantity Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionQuantity() {
		log.debug("Verifying Transaction Quantity");
		assertEquals(transaction.getQuantity(), 100);
		log.debug("Transaction Quantity Successfully Verified");
	}

	@Test
	public void updateTransactionTotal() {
		log.debug("Updating Transaction Total: \"" + transaction.getTotal() + "\" to \"1000\"");
		transaction.setTotal(BigDecimal.valueOf(1000));
		log.debug("Transaction Total Successfully Updated");
	}

	@Test
	public void verifyUpdatedTransactionTotal() {
		log.debug("Verifying Transaction Total");
		assertEquals(transaction.getTotal().toString(), "1000");
		log.debug("Transaction Total Successfully Verified");
	}

	@AfterClass
	public void afterClass() {
		log.debug("Transaction Created & Verified Successfully.");
		log.debug("End of Transaction Test");
	}
}
