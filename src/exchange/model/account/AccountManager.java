package exchange.model.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import exchange.model.database.DataBaseAdmin;


public class AccountManager 
{
	public void addAccount(String id, String password, String userName, String nickName, boolean gender, String email, String birthday, String region, int skillMax, int skillNumber) throws SQLException
	{
		Date recentLog = new Date();
		java.sql.Date sqlStartDate = new java.sql.Date(recentLog.getTime());
		String query = "INSERT INTO accounts VALUES ('"+id+"', '"+password+"', '"+userName+"', '"+nickName+"', '"+gender+"', "
				+ "'"+email+"', '"+birthday+"' ,'"+region+"', '"+skillMax+"', '"+skillNumber+"', '"+sqlStartDate+"')";
		DataBaseAdmin.updateDB(query);
	}
	public Account getAccount(String id) throws SQLException
	{
		ResultSet result = null;
		result = DataBaseAdmin.selectDB("SELECT * FROM 'accounts' where user_id = '"+id+"'");
		result.next();
		Secret secret = new Secret(id, result.getString("password"));
		Profile profile = new Profile(result.getString("user_name"), result.getString("nick_name"), 
				result.getBoolean("gender"), result.getString("email"), result.getString("birthday"), 
				result.getString("region"), result.getInt("skill_max"), result.getInt("skill_number"));
		
		Account account = new Account(secret, profile, result.getDate("recent_log"));
		
		return account;
		
	}
	
	public ArrayList getAllUserId() throws SQLException
	{
		ArrayList<Secret> idlist = new ArrayList<>();
		String query = "SELECT * FROM 'user_id' ";
		ResultSet result = null;
		result = DataBaseAdmin.selectDB(query);
		while(result.next())
			idlist.add(new Secret(result.getString("user_id")));
		return idlist;
	}
	//Profile修改
	public void setProfile(Profile profile)
	{
		Secret s = new Secret();
		Profile p = new Profile();
		String id = s.getId();
		String nickName = p.getnickName();
		String email = p.getEmail();
		String region = p.getEmail();
		String query = "UPDATE accounts SET nick_name = '"+nickName+"', email = '"+email+"', region = '"+region+"' "
				+ "where user_id = '"+id+"'";
		DataBaseAdmin.updateDB(query);
	}
	//Secret修改
	public void setSecret(Secret secret)
	{
		Secret s = new Secret();
		String id = s.getId();
		String password = s.getPassword();
		String query = "UPDATE accounts SET password = '"+password+"' "
				+ "where user_id = '"+id+"'";
		DataBaseAdmin.updateDB(query);
	}
	
	public static boolean isSkillFull(String id) throws SQLException
	{
		boolean result;

		String query = "select * from accounts where user_id = '"+id+"'";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		if(rs.getInt("skill_number") > rs.getInt("skill_max"))
			result = false;
		else
			result = true;
		return result;
	}
	
	public boolean isValid(String id) throws SQLException
	{
		AccountManager am = new AccountManager();
		if(id.equals(am.getAllUserId()))
			result = false;
		else if(length > 20)
			result = false;
		else 
			result = true;	
		return result;
	}
}
