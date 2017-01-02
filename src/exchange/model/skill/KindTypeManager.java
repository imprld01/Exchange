package exchange.model.skill;

import exchange.model.database.DataBaseAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KindTypeManager {

	ArrayList<Kind> kindList;
	ArrayList<Type> typeList;

	public KindTypeManager() throws SQLException {
		kindList = new ArrayList<Kind>();
		typeList = new ArrayList<Type>();

		updateKindList();
		updateTypeList();
	}

	public void updateKindList() throws SQLException {

		String query = "SELECT * FROM classes";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		while (rs.next()) {	
			kindList.add(new Kind(
					new Code(rs.getString("class_code")), 
					rs.getString("class_name"))
					);
		}
	}

	public void updateTypeList() throws SQLException {

		String query = "SELECT * FROM types";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		while (rs.next()) {
			typeList.add(new Type(
					new Code(rs.getString("type_code")), 
					rs.getString("type_name"),
					new Code(rs.getString("class_code"))
					));
		}
	}

	@Override
	public String toString() {
		String output = new String();

		output = "kindList" + kindList + "\ntypeList" + typeList;		
		return output;
	}
}
