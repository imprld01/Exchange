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
import exchange.model.match.Region;

public class AccountManagerTest {
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

	@Test //新增不存在的id
	public void testAddAccount1() throws SQLException {
		AccountManager am = new AccountManager();
		String input_id = "15133131";
		String input_password = "1313131";
	    String input_userName = "tick"; 
		String input_nickName = "fire";
		boolean input_gender = true;
		String input_email = "@gmail.com";
		String input_birthday = "2016-09-05"; 
		String input_region = "台北";
		boolean output = true;
		boolean input =am.addAccount(input_id, input_password, 
				input_userName, input_nickName, input_gender, input_email, 
				input_birthday, input_region);
		assertEquals(input, output);
	}

	@Test //新增存在的id
	public void testAddAccount2() throws SQLException {
		AccountManager am = new AccountManager();
		String input_id = "10567026";
		String input_password = "k6666894";
	    String input_userName = "鍾子健"; 
		String input_nickName = "nick2";
		boolean input_gender = false;
		String input_email = "email1515";
		String input_birthday = "1994-07-27"; 
		String input_region = "region882";
		boolean output = false;
		boolean input = am.addAccount(input_id, input_password, 
				input_userName, input_nickName, input_gender, input_email, 
				input_birthday, input_region);
		assertEquals(input, output);
	}
	
	@Test //測試修改成功 輸入已存在user_id
	public void testSetProfie1(){
		AccountManager am = new AccountManager();
		int input = am.setProfile("10567026", new Profile("nick3223", "345434", "台北"));
		int output = 1;
		assertEquals(input, output);
	}
	
	@Test //測試修改失敗 輸入不存在user_id
	public void testSetProfie2(){
		AccountManager am = new AccountManager();
		int input = am.setProfile("105151151516", new Profile("nick3223", "345434", "台北"));
		int output = 0;
		assertEquals(input, output);
	}
	
	@Test //測試修改成功 輸入已存在user_id
	public void testSetSecet1(){
		AccountManager am = new AccountManager();
		int input = am.setSecret(new Secret("10567026", "dsdsdsds"));
		int output = 1;
		assertEquals(input, output);
	}
	
	@Test //測試修改失敗 輸入不存在user_id
	public void testSetSecet2(){
		AccountManager am = new AccountManager();
		int input = am.setSecret(new Secret("5135551", "dsdsds"));
		int output = 0;
		assertEquals(input, output);
	}
	
	@Test //測試技能沒滿  輸入存在user_id
	public void testIsSkillFull1() throws SQLException{
		AccountManager am = new AccountManager();
		boolean input = am.isSkillFull("10567026");
		boolean output = false;
		assertEquals(input, output);
	}
	
	@Test //測試技能已滿  輸入存在user_id
	public void testIsSkillFull2() throws SQLException{
		AccountManager am = new AccountManager();
		boolean input = am.isSkillFull("bobobo");
		boolean output = true;
		assertEquals(input, output);
	}
	
	@Test //測試錯誤  輸入不存在user_id
	public void testIsSkillFull3() throws SQLException{
		AccountManager am = new AccountManager();
		boolean input = am.isSkillFull("bob15115151obo");
		boolean output = true;
		assertEquals(input, output);
	}
	
	@Test //測試帳號相同  輸入存在user_id
	public void testIsValid1() throws SQLException{
		AccountManager am = new AccountManager();
		boolean input = am.isValid("bobobo");
		boolean output = true;
		assertEquals(input, output);
	}
	
	@Test //測試帳號不存在  輸入不存在user_id
	public void testIsValid2() throws SQLException{
		AccountManager am = new AccountManager();
		boolean input = am.isValid("bob561651obo");
		boolean output = false;
		assertEquals(input, output);
	}
	
	@Test //測試地區相同  輸入存在user_id
	public void testGetRegion1() throws SQLException{
		AccountManager am = new AccountManager();
		String input = am.getRegion("0");
		String output = "台北";
		assertEquals(input, output);
	}
	
	@Test //測試地區相同  輸入不存在user_id
	public void testGetRegion2() throws SQLException{
		AccountManager am = new AccountManager();
		String input = am.getRegion("1056666667026");
		String output = null;
		assertEquals(input, output);
	}
	
	@Test //測試基隆
	public void testToRegionObj1() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("基隆");
		Region output = Region.KEELUNG;
		assertEquals(input, output);
	}
	
	@Test //測試台北
	public void testToRegionObj2() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("台北");
		Region output = Region.TAIPEI;
		assertEquals(input, output);
	}
	
	@Test //測試桃園
	public void testToRegionObj3() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("桃園");
		Region output = Region.TAOYUAN;
		assertEquals(input, output);
	}
	
	@Test //測試新竹
	public void testToRegionObj4() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("新竹");
		Region output = Region.HSINCHU;
		assertEquals(input, output);
	}
	
	@Test //測試苗栗
	public void testToRegionObj5() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("苗栗");
		Region output = Region.MIAOLI;
		assertEquals(input, output);
	}
	
	@Test //測試台中
	public void testToRegionObj6() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("台中");
		Region output = Region.TAICHUNG;
		assertEquals(input, output);
	}
	
	@Test //測試彰化
	public void testToRegionObj7() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("彰化");
		Region output = Region.CHANGHUA;
		assertEquals(input, output);
	}
	
	@Test //測試南投
	public void testToRegionObj8() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("南投");
		Region output = Region.NANTOU;
		assertEquals(input, output);
	}
	
	@Test //測試雲林
	public void testToRegionObj9() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("雲林");
		Region output = Region.YUNLIN;
		assertEquals(input, output);
	}
	
	@Test //測試嘉義
	public void testToRegionObj10() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("嘉義");
		Region output = Region.CHIAYI;
		assertEquals(input, output);
	}
	
	@Test //測試台南
	public void testToRegionObj11() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("台南");
		Region output = Region.TAINAN;
		assertEquals(input, output);
	}
	
	@Test //測試高雄
	public void testToRegionObj12() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("高雄");
		Region output = Region.KAOHSIUNG;
		assertEquals(input, output);
	}
	
	@Test //測試屏東
	public void testToRegionObj13() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("屏東");
		Region output = Region.PINGTUNG;
		assertEquals(input, output);
	}
	
	@Test //測試宜蘭
	public void testToRegionObj14() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("宜蘭");
		Region output = Region.ILAN;
		assertEquals(input, output);
	}
	
	@Test //測試花蓮
	public void testToRegionObj15() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("花蓮");
		Region output = Region.HUALIEN;
		assertEquals(input, output);
	}
	
	@Test //測試台東
	public void testToRegionObj16() throws SQLException{
		AccountManager am = new AccountManager();
		Region input = am.toRegionObj("台東");
		Region output = Region.TAITUNG;
		assertEquals(input, output);
	}
}
