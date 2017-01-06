package exchange.model.sign;

import java.sql.ResultSet;
import java.sql.SQLException;

import exchange.model.account.Account;
import exchange.model.account.AccountManager;
import exchange.model.account.Profile;
import exchange.model.account.Secret;
import exchange.model.database.DataBaseAdmin;

public class SignManager {

	public boolean check(Secret secret) throws SQLException{
		boolean result;
		SignManager sm = new SignManager();
		if(sm.isAccountValid(secret.getId()) && sm.CheckPassword(secret.getPassword()) == true)
			result = true;
		else if(sm.isAccountValid(secret.getId()) || sm.CheckPassword(secret.getPassword()) == false)
			result = false;
		else 
			result = false;
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
	
	public boolean isAccountValid(String id) throws SQLException{
		AccountManager am = new AccountManager();
		return am.isValid(id);
	}
	
	
}