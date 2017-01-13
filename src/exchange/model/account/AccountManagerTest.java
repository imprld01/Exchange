package exchange.model.account;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountManagerTest {
	private AccountManager accountmanager;
	private String tAA1[][] = {{"10567026"}, {"kc567894"},{}};
	private String tAA2[][] = {{"1234567"},{"00000"}};

	@Before
	public void setUp() throws Exception {
		accountmanager = new AccountManager();
	}

	@After
	public void tearDown() throws Exception {
		accountmanager = null;
	}

	@Test
	public void testAddAccount() {
		fail("Not yet implemented");
		
		
		
		
	}

}
