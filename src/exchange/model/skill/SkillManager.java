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

	static public void createSkill(Skill skill) {
		// 判斷是否可新增技能
		int skillId = 0;
		String userId = skill.getUserId();
		String typeName = skill.getType().getTypeName();

		try {

			if (AccountManager.isSkillFull(userId)) {

				DataBaseAdmin.updateDB("INSERT INTO skills VALUES('0','" + userId + "','" + typeName + "','"
						+ skill.getIntorExpr() + "','0','0','0','0','0','0','0','0','0')");

				ResultSet rs = DataBaseAdmin.selectDB(
						"SELECT * FROM skills where user_id = '" + userId + "' AND type_name ='" + typeName + "'");
				try {
					if (rs.next()) {
						skillId = rs.getInt("skill_id");
					}
					System.out.println("SKILL_ID:" + skillId);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				for (String vedio : skill.getVedio())
					DataBaseAdmin.updateDB("INSERT INTO vedios VALUES('" + skillId + "','" + vedio + "')");

				for (String image : skill.getImage())
					DataBaseAdmin.updateDB("INSERT INTO vedios VALUES('" + skillId + "','" + image + "')");
			} else {
				System.out.println(userId + " 技能已達上限");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public void modifySkill(Skill skill) {
		
	}

	static public void createFavoriteSkill(String userId,String typeName) {

	}

	static public void deleteFavoriteSkill(String userId,String typeName) {

	}

	static public ArrayList<Skill> getAllSkills(String userId) {

		ArrayList<Skill> skills = new ArrayList<Skill>();
		String query = "SELECT * FROM skills WHERE user_id = '" + userId + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		try {
			while (rs.next()) {
				skills.add(new Skill(rs.getInt("skill_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return skills;
	}

	static public ArrayList<Type> getAllFavoriteSkills(String userId) {

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
