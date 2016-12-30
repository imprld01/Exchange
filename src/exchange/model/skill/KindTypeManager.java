package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KindTypeManager {

	ArrayList<Kind> kindList;
	ArrayList<Type> typeList;
	
	public KindTypeManager() throws SQLException
	{
		kindList = new ArrayList<Kind>();
		typeList = new ArrayList<Type>();
		
		insertKindList();
	}
	
	public void insertKindList() throws SQLException{
		
		ConnectDB.openConnection();
		ResultSet rs = ConnectDB.get();
		while (rs.next()) {
			//System.out.println(rs.getString("class_code") + "\t" + rs.getString("class_name"));
			kindList.add(new Kind(new Code(rs.getString("class_code")),rs.getString("class_name")));
		}
	}
	
	@Override
	public String toString()
	{
		String output = new String();
		
		output = "kindLis";

			output += kindList;

		
		return output;
	}
}
