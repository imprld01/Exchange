package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.account.AccountManager;
import exchange.model.database.DataBaseAdmin;

public class SkillManager {

	public boolean isSendingInvation() {

		// 判斷卡片是否再invitation的sender中
		return false;
	}

	static public void createSkill(Skill skill) throws SQLException {
		// 判斷是否可新增技能
		int skillId = 0;
		String userId = skill.getUserId();
		String typeName = skill.getType().getTypeName();

		if (AccountManager.isSkillFull(userId)) {

			DataBaseAdmin.updateDB("INSERT INTO skills VALUES('0','" + userId + "','" + typeName + "','"
					+ skill.getIntorExpr() + "','0','0','0','0','0','0','0','0','0')");
			ResultSet rs = DataBaseAdmin.selectDB(
					"SELECT * FROM skills where user_id = '" + userId + "' AND type_name ='" + typeName + "'");

			if (rs.next())
				skillId = rs.getInt("skill_id");

			for (String vedio : skill.getVedio())
				DataBaseAdmin.updateDB("INSERT INTO vedio VALUES('" + skillId + "','" + vedio + "')");

			for (String image : skill.getImage())
				DataBaseAdmin.updateDB("INSERT INTO image VALUES('" + skillId + "','" + image + "')");
		} else {
			System.out.println(userId + " 技能已達上限");
		}

	}

	static public void modifySkill(Skill skill) {

		int skillId = skill.getSkillId();
		DataBaseAdmin.updateDB(
				"UPDATE skills SET intro_expr='" + skill.getIntorExpr() + "' where (skill_id = '" + skillId + "')");

		for (String vedio : skill.getVedio())
			DataBaseAdmin.updateDB("UPDATE videos SET vedio='" + vedio + "' where (skill_id = '" + skillId + "')");

		for (String image : skill.getImage())
			DataBaseAdmin.updateDB("UPDATE images SET image='" + image + "' where (skill_id = '" + skillId + "')");
	}

	static public void createFavoriteSkill(String typeName, String userId) {
		DataBaseAdmin.updateDB("INSERT INTO favorites VALUES('" + typeName + "','" + userId + "')");
	}

	static public void deleteFavoriteSkill(String typeName, String userId) {
		DataBaseAdmin.updateDB("DELETE FROM favorites Where user_id= '"+ userId +"'AND type_name = '"+ typeName+"'");
	}

	static public ArrayList<Skill> getAllSkills(String userId) throws SQLException {

		ArrayList<Skill> skills = new ArrayList<Skill>();
		String query = "SELECT * FROM skills WHERE user_id = '" + userId + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		while (rs.next()) {
			skills.add(new Skill(rs.getInt("skill_id")));
		}

		return skills;
	}

	static public ArrayList<Type> getAllFavoriteSkills(String userId) throws SQLException {

		ArrayList<Type> favoriteTypes = new ArrayList<Type>();
		String query = "SELECT * FROM favorites WHERE user_id = '" + userId + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		while (rs.next())
			favoriteTypes.add(new Type(rs.getString("type_name")));

		return favoriteTypes;
	}

}
