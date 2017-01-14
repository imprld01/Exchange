package exchange.model.account;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;

public class AccountManagerTest {
	private AccountManager accountmanager;

	@Before
	public void setUp() throws Exception {
		DataBaseAdmin db = new DataBaseAdmin();
		db.changeDBAccount("root", "root");
		accountmanager = new AccountManager();
	}

	@After
	public void tearDown() throws Exception {
		accountmanager = null;
	}

//	@Test
//	public void testAddAccount1() throws SQLException {
//		AccountManager am = new AccountManager();
//		String input_id = "15133131";
//		String input_password = "1313131";
//	    String input_userName = "tick"; 
//		String input_nickName = "fire";
//		boolean input_gender = true;
//		String input_email = "@gmail.com";
//		String input_birthday = "2016-09-05"; 
//		String input_region = "台北";
//		boolean output = true;
//		assertEquals(am.addAccount(input_id, input_password, 
//				input_userName, input_nickName, input_gender, input_email, 
//				input_birthday, input_region), output);
//	}

	@Test
	public void testAddAccount2() throws SQLException {
		AccountManager am = new AccountManager();
		String input_id = "10567026";
		String input_password = "kc567894";
	    String input_userName = "鍾子健"; 
		String input_nickName = "nick2";
		boolean input_gender = false;
		String input_email = "email1515";
		String input_birthday = "1994-07-27"; 
		String input_region = "region882";
		boolean output = false;
		assertEquals(am.addAccount(input_id, input_password, 
				input_userName, input_nickName, input_gender, input_email, 
				input_birthday, input_region), output);
	}
//	
//	@Test
//	public void testGetAccount() throws ParseException{
//		AccountManager am = new AccountManager();
//		String input = "16511";
//		Account output = new Account(new Secret("16511", "15111"), new Profile("16516161", "65151", false, "156131", "2016-01-11", "台北"), );
//		assertEquals(am.getAccount(input), output);
//	}
	
	@Test
	public void testSetProfie1(){
		
	}
	
	
}
