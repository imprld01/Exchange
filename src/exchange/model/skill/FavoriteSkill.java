package exchange.model.skill;

import exchange.model.database.DataBaseAdmin;

public class FavoriteSkill {
	private Type type;
	
	public FavoriteSkill(String user_id)
	{
		query = "SELECT * FROM types WHERE type_name = '" + type_name + "'";
		rs = DataBaseAdmin.selectDB(query);
		if (rs.next()) {
			type = new Type(new Code(rs.getString("type_code")),
					rs.getString("type_name"),
					new Code(rs.getString("class_code")));
		}
	}
}
 