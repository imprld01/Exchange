package exchange.model.account;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;

public class AccountManagerTest2 {
	
	private AccountManager accountmanager;

	@Before
	public void setUp() throws Exception {
		DataBaseAdmin.openConnection("root", "narutoap12");
		accountmanager = new AccountManager();
	}

	@After
	public void tearDown() throws Exception {
		accountmanager = null;
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
