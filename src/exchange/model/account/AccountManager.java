package exchange.model.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import exchange.model.database.DataBaseAdmin;
import exchange.model.match.Region;

public class AccountManager {
	public boolean addAccount(String id, String password, String userName, String nickName, boolean gender, String email,
			String birthday, String region) throws SQLException {
		int result = 0;
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		int skillMax = 3;
		int skillNumber = 0;
		int gender_int = 0;
		if (gender) {
			gender_int = 1;
		} else {
			gender_int = 0;
		}
		String query = "INSERT INTO accounts VALUES ('" + id + "', '" + password + "', '" + userName + "', '" + nickName
				+ "', '" + gender_int + "', " + "'" + email + "', '" + birthday + "' ,'" + region + "', '" + skillNumber
				+ "', '" + skillMax + "', '" + sqlStartDate + "')";
		result = DataBaseAdmin.updateDB(query);
		return (result==0)? false:true;
	}

	public Account getAccount(String id) {
		Account account = null;
		try {
			ResultSet result = DataBaseAdmin.selectDB("SELECT * FROM accounts where user_id = '" + id + "'");

			if (result.next()) {

				Secret secret = new Secret(id, result.getString("password"));
				Profile profile = new Profile(result.getString("user_name"), result.getString("nick_name"),
						result.getBoolean("gender"), result.getString("email"), result.getString("birthday"),
						result.getString("region"), result.getInt("skill_max"), result.getInt("skill_number"));
				
				account = new Account(secret, profile, result.getDate("recent_log"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("account->"+account);
		return account;

	}

	public ArrayList<Secret> getAllUserId() throws SQLException {
		ArrayList<Secret> idlist = new ArrayList<>();
		String query = "SELECT * FROM accounts ";
		ResultSet result = null;
		result = DataBaseAdmin.selectDB(query);
		while (result.next())
			idlist.add(new Secret(result.getString("user_id")));

		return idlist;
	}

	// Profile修改
	public boolean setProfile(String id, Profile profile) {
		boolean result = false;
		String nickName = profile.getNickName();
		String email = profile.getEmail();
		String region = profile.getRegion();
		try{
			String query = "UPDATE accounts SET nick_name = '" + nickName + "', email = '" + email + "', region = '"
					+ region + "' " + "where user_id = '" + id + "'";
			DataBaseAdmin.updateDB(query);
			result = true;
		}catch(Exception e){
			
		}
		return result;

	}

	// Secret修改
	public boolean setSecret(Secret secret) {
		boolean result = false;
		String id = secret.getId();
		String password = secret.getPassword();
		try{
			String query = "UPDATE accounts SET password = '" + password + "' " + "where user_id = '" + id + "'";
			DataBaseAdmin.updateDB(query);
			result = true;
		}catch(Exception e){
			
		}
		return result;

	}

	public static boolean isSkillFull(String id) throws SQLException {
		boolean result = true;
		String query = "select * from accounts where user_id = '" + id + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		rs.next();
		if (rs.getInt("skill_number") < rs.getInt("skill_max"))
			result = false;

		return result;
	}

	// 驗證帳號登入
	public boolean isValid(String id) throws SQLException {
		boolean result = false;
		String query = "SELECT * FROM accounts where user_id='" + id + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		rs.next();
		//System.out.println("[" + id + "] -> [" + rs.getString("user_id") + "]");
		if (id.equals(rs.getString("user_id")))
			result = true;
		// else if (id.length() > 20)
		// result = false;
		// else if (id == null)
		// result = false;
		// else
		// result = false;
		return result;
	}

	public String getRegion(String id) {
		String query = "SELECT * FROM accounts where user_id = '" + id + "' ";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		try {
			rs.next();
			//System.out.println("[region]->"+rs.getString("region"));
			return rs.getString("region");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Region toRegionObj(String region){
		
		switch(region){
		case "基隆": return Region.KEELUNG;
		case "台北": return Region.TAIPEI;
		case "桃園": return Region.TAOYUAN;
		case "新竹": return Region.HSINCHU;
		case "苗栗": return Region.MIAOLI;
		case "台中": return Region.TAICHUNG;
		case "彰化": return Region.CHANGHUA;
		case "南投": return Region.NANTOU;
		case "雲林": return Region.YUNLIN;
		case "嘉義": return Region.CHIAYI;
		case "台南": return Region.TAINAN;
		case "高雄": return Region.KAOHSIUNG;
		case "屏東": return Region.PINGTUNG;
		case "宜蘭": return Region.ILAN;
		case "花蓮": return Region.HUALIEN;
		case "台東": return Region.TAITUNG;
		}
		
		return null;
	}
}
