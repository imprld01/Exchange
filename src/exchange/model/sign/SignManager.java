package exchange.model.sign;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import exchange.model.account.Account;
import exchange.model.account.AccountManager;
import exchange.model.account.Secret;
import exchange.model.database.DataBaseAdmin;

public class SignManager {

	public boolean check(Secret secret) {
		boolean result = false;
		AccountManager am = new AccountManager();
		SignManager sm = new SignManager();
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		try {
			if (am.isValid(secret.getId()) == true) {
				result = true;
				if (sm.CheckPassword(secret.getPassword()) == true) {
					result = true;
					String query = "UPDATE accounts SET recent_Log = '" + sqlStartDate + "'";
					DataBaseAdmin.updateDB(query);
				} else
					result = false;
			} else
				result = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseAdmin.closeConnection();
		return result;
	}

	public boolean CheckPassword(String password) {
		boolean result = false;
		String query = "SELECT * FROM accounts";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		try {
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (password.equals(rs.getString("password")))
				result = true;
			else if (password == null)
				result = false;
			else
				result = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void create(Account account) {
		AccountManager am = new AccountManager();
		try {
			am.addAccount(account.getSecret().getId(), account.getSecret().getPassword(),
					account.getProfile().getUserName(), account.getProfile().getNickName(),
					account.getProfile().getGender(), account.getProfile().getEmail(),
					account.getProfile().getBirthday(), account.getProfile().getRegion());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 確認帳號格式，重複與否
	public boolean isAccountValid(String id) {
		boolean result = true;
		String query = "SELECT * FROM accounts";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		try {
			if (rs.next()) 
			{
				if (id.equals(rs.getString("user_id")))
					result = false;
				// else if (id.length() > 20)
				// result = false;
				// else if (id == null)
				// result = false;
				// else
				// result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	static public void main(String args[]) {
		SignManager sm = new SignManager();

		System.out.println(sm.isAccountValid("0"));
	}

}