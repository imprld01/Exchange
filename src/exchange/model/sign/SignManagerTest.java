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
	
	@Test //輸入正確帳號密碼
	public void testCheck1() {
		Secret input = new Secret("10567026", "kc567894");
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}

	@Test  //輸入錯誤的帳號 正確的密碼
	public void testCheck2() {
		Secret input = new Secret("105676", "kc567894");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test  //輸入正確的帳號 錯誤的密碼
	public void testCheck3() {
		Secret input = new Secret("10567026", "kc5674");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test //輸入錯誤的帳號密碼
	public void testCheck4() {
		Secret input = new Secret("105026", "kc5674");
		boolean output = false;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test //輸入存在的user_id與正確的密碼
	public void testCheckPassword1() {
		Secret input = new Secret("10567026", "kc567894");
		boolean output = true;
		SignManager sm = new SignManager();
		assertEquals(sm.CheckPassword(input), output);
	}
	
	@Test  //輸入存在的user_id與錯誤的密碼
	public void testCheckPassword2() {
		Secret input_Secret = new Secret("10567026", "nccejelfjel");
		boolean output = false;
		SignManager sm = new SignManager();
		boolean input = sm.CheckPassword(input_Secret);
		assertEquals(input, output);
	}
	
	@Test  //輸入不存在的user_id
	public void testCheckPassword3() {
		Secret input_Secret = new Secret("105670666626", "nccejelfjel");
		boolean output = false;
		SignManager sm = new SignManager();
		boolean input = sm.CheckPassword(input_Secret);
		assertEquals(input, output);
	}
	
	@Test  //輸入已存在的user_id判斷已存在
	public void testIsAccountValid1(){
		SignManager sm = new SignManager();
		boolean input = sm.isAccountValid("1234567");
		boolean output = false;
		assertEquals(input, output);
	}

	@Test  //輸入不存在的user_id判斷不存在
	public void testIsAccountValid2(){
		SignManager sm = new SignManager();
		boolean input = sm.isAccountValid("165645616");
		boolean output = true;
		assertEquals(input, output);
	}
	
	@Test  //輸入帳號資料，建立成功
	public void testCreate1(){
		Secret secret = new Secret("15561315", "gd55kdls");
		Profile profile = new Profile("ck66ja", "slmcs66mc",
				true, "djjf66kkfd", "2016-09-06", "台北", 3, 0);
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		Account input_Account = new Account(secret, profile, sqlStartDate);
		boolean output = true;
		SignManager sm = new SignManager();
		boolean input = sm.create(input_Account);
		assertEquals(input, output);
      }
	
	@Test //輸入有null 建立失敗
	public void testCreate2(){
		Secret secret = new Secret(null, "gddlkdls");
		Profile profile = new Profile("ckja", "slmcsmc",
				true, "djjfkkfd", "2016-09-06", "台北", 3, 0);
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		Account input_Account = new Account(secret, profile, sqlStartDate);
		SignManager sm = new SignManager();
		boolean input = sm.create(input_Account);
		boolean output = false;
		assertEquals(input, output);
	}
}
