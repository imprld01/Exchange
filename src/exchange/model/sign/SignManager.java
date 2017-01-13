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
			//System.out.println("[帳號] -> ["+ secret.getPassword() +"]");
			if (am.isValid(secret.getId()) == true) { // 帳號是否存在
				//System.out.println("[帳號存在，比對密碼]");
				if (sm.CheckPassword(secret) == true) { // 密碼是否相同
					result = true;
					String query = "UPDATE accounts SET recent_Log = '" + sqlStartDate + "'";
					DataBaseAdmin.updateDB(query);
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// DataBaseAdmin.closeConnection();
		return result;
	}

	public boolean CheckPassword(Secret secret) {
		boolean result = false;
		String query = "SELECT * FROM accounts where user_id='" + secret.getId() + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		try {
			rs.next();
			//System.out.println("["+secret.getPassword()+"] -> ["+ rs.getString("password") +"]");
			if (secret.getPassword().equals(rs.getString("password")))
				result = true;
		} catch (SQLException e) {
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

		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM accounts WHERE user_id='"+ id +"'");
		try {
			if (rs.next()) {
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
			e.printStackTrace();
		}
		return result;
	}

	static public void main(String args[]) {
		SignManager sm = new SignManager();

		System.out.println(sm.CheckPassword(new Secret("bobobo","bobobo")));
	}

}