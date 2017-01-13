package exchange.model.sign;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.account.Account;
import exchange.model.account.Profile;
import exchange.model.account.Secret;

public class SignManagerTest {
	private SignManager signmanager;

	@Before
	public void setUp() throws Exception {
		signmanager = new SignManager();
	}

	@After
	public void tearDown() throws Exception {
		signmanager = null;
	}
	
	@Test
	public void testCheck1() {
		Secret input = new Secret("10567026", "kc567894");
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}

	@Test
	public void testCheck2() {
		Secret input = new Secret("105676", "kc567894");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test
	public void testCheck3() {
		Secret input = new Secret("10567026", "kc5674");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test
	public void testCheck4() {
		Secret input = new Secret("105026", "kc5674");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
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
		assertEquals(sm.isAccountValid(input), output);
	}

	@Test
	public void testIsAccountValid2(){
		String input = "165645616";
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.isAccountValid(input), output);
	}
	
	@Test
	public void testCreate1(){
		Secret secret = new Secret("15561315", "gd55kdls");
		Profile profile = new Profile("ck66ja", "slmcs66mc",
				true, "djjf66kkfd", "2016-09-06", "台北", 3, 0);
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		Account input = new Account(secret, profile, sqlStartDate);
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.create(input), output);
      }
	
	@Test
	public void testCreate2(){
		Secret secret = new Secret("15631315", "gddlkdls");
		Profile profile = new Profile("ckja", "slmcsmc",
				true, "djjfkkfd", "2016-09-06", "台北", 3, 0);
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		Account input = new Account(secret, profile, sqlStartDate);
		//0為失敗 其他數字為成功
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.create(input), output);
	}
}
