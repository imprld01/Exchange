package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;

public class SkillManager {

	public boolean isSendingInvation() {

		// 判斷卡片是否再invitation的sender中
		return false;
	}

	public ArrayList<Type> getAllFavoriteSkills(String userId) {

		ArrayList<Type> favoriteTypes = new ArrayList<Type>();
		String query = "SELECT * FROM favorites WHERE user_id = '" + userId + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		try {
			while (rs.next()) {
				favoriteTypes.add(new Type(rs.getString("type_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return favoriteTypes;
	}
}
