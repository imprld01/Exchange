package exchange.model.account;

import java.sql.SQLException;
import java.util.Date;

import exchange.model.database.DataBaseAdmin;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		//Date recentLog = new Date();
		//java.sql.Date now = new java.sql.Date(recentLog.getTime());
		//System.out.println(now);
		DataBaseAdmin db = new DataBaseAdmin();
		db.changeDBAccount("root", "narutoap12");
		
		AccountManager am = new AccountManager();
		//System.out.println(am.getAccount("10567026"));
		//System.out.println(am.getAccount("1234567"));
		
		//am.addAccount("1234567", "859413", "Jeremy", "nick", 1, "251313", "1994-07-27", "台北", 3, 3);
		//System.out.println(am.getAllUserId());
		//System.out.println(am.isValid("10567026"));
		//System.out.println(am.getRegion("10567026"));
		//System.out.println();
		//Profile profile = new Profile("nickName", "email", "region");
		//am.setProfile(profile);
	}

}
