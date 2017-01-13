package exchange.model.sign;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.account.Secret;
import exchange.model.database.DataBaseAdmin;

public class SignManagerTest {
	private SignManager signmanager;

	@Before
	public void setUp() throws Exception {
		DataBaseAdmin db = new DataBaseAdmin();
		db.changeDBAccount("root", "narutoap12");
		signmanager = new SignManager();
	}

	@After
	public void tearDown() throws Exception {
		signmanager = null;
	}

	@Test
	public void testCheckPassword1() {
		Secret input = new Secret("10567026", "kc567894");
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test
	public void testCheckPassword2() {
		Secret input = new Secret("10567026", "nccejelfjel");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test
	public void testIsAccountValid1(){
		String input = "1234567";
		boolean output = false;
		SignManager sm = new SignManager();
		System.out.println(sm.isAccountValid(input));
		assertEquals(sm.isAccountValid(input), output);
	}

	@Test
	public void testIsAccountValid2(){
		String input = "165645616";
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.isAccountValid(input), output);
	}
}
