package exchange.model.sign;

import java.sql.SQLException;

import exchange.model.account.Account;
import exchange.model.account.AccountManager;
import exchange.model.account.Profile;
import exchange.model.account.Secret;
import exchange.model.database.DataBaseAdmin;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		DataBaseAdmin db = new DataBaseAdmin();
		db.changeDBAccount("root", "root");
		SignManager sm = new SignManager();
		
		//System.out.println();
		
		//check(secret) 
		//Secret secret = new Secret("151211", "kc567894");
		//Secret secret = new Secret("10567026", "kc567894");
		//Secret secret = new Secret("10567026", "123456789");
		//Secret secret = new Secret("", "kc567894");
		//Secret secret = new Secret("10567026", "");
		//Secret secret = new Secret("", "");
		//System.out.println(sm.check(secret));
		
		//isAccountVaild(id) success
		System.out.println(sm.isAccountValid("test"));
		
		//CheckPassword(password)
		//System.out.println(sm.CheckPassword("kc567894"));
		
		//create()
		//Secret secret = new Secret("975183113", "62313513843");
		//Profile profile = new Profile("Tick", "nick", true, "email", "1516-05-13", "region");
		//sm.create(new Account(profile, secret));
	}

}
