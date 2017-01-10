package exchange.model.sign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import exchange.model.account.Account;
import exchange.model.account.AccountManager;
import exchange.model.account.Secret;
import exchange.model.database.DataBaseAdmin;

public class SignManager {

	public boolean check(Secret secret) throws SQLException{
		boolean result;
		AccountManager am = new AccountManager();
		SignManager sm = new SignManager();
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		if(am.isValid(secret.getId()) == true)
		{
			result = true;
			if(sm.CheckPassword(secret.getPassword()) == true)
			{
				result = true;
				String query = "UPDATE accounts SET recent_Log = '" + sqlStartDate +"'" ;
				DataBaseAdmin.updateDB(query);
			}
			else 
				result = false;
		}   		
		else 
			result = false;
		DataBaseAdmin.closeConnection();
		return result;
	}
	
	public boolean CheckPassword(String password) throws SQLException
	{
		boolean result;
		String query = "SELECT * FROM accounts";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		rs.next();
		if(password.equals(rs.getString("password")))
			result = true;
		else if(password == null)
			result = false;
		else
			result = false;
		return result;
	}
	
	public void create(Account account) throws SQLException{
		AccountManager am = new AccountManager();
		am.addAccount(account.getSecret().getId(), account.getSecret().getPassword(),
		account.getProfile().getUserName(), account.getProfile().getNickName(), account.getProfile().getGender(), 
		account.getProfile().getEmail(), account.getProfile().getBirthday(), account.getProfile().getRegion());
	}
	
//確認帳號格式，重複與否
	public boolean isAccountValid(String id) throws SQLException{
		boolean result;
		String query = "SELECT * FROM accounts";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		rs.next();
		if (id.equals(rs.getString("user_id")))
			result = false;
		else if (id.length() > 20)
			result = false;
		else if(id == null)
			result = false;
		else
			result = true;
		return result;
	}
}