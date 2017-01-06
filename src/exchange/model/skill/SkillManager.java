package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.account.AccountManager;
import exchange.model.database.DataBaseAdmin;

public class SkillManager {

	static public Skill findSkill(int skillId) {
		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM skills WHERE skill_id = '" + skillId + "'");
		Skill skill = new Skill();
		
		try {
			if (rs.next()) {

				String userId = rs.getString("user_id");
				String typeName = rs.getString("type_name");
				String introExpr = rs.getString("intro_expr");
				int skillLevel = rs.getInt("skill_level");
				int times = rs.getInt("times");
				Score score = new Score(rs.getInt("attitude_score"), rs.getInt("profession_score"),
						rs.getInt("teaching_score"), rs.getInt("frequency_score"), rs.getInt("satisfication_score"));
				boolean warningTag = (rs.getBoolean("bad_tag")) ? true : false;
				boolean badTag = (rs.getBoolean("warning_tag")) ? true : false;
				ArrayList<Comment> comment = new ArrayList<Comment>();
				ArrayList<String> image = new ArrayList<String>();
				ArrayList<String> video = new ArrayList<String>();

				rs = DataBaseAdmin.selectDB("SELECT * FROM comments WHERE skill_id = '" + skillId + "'");
				while (rs.next())
					comment.add(new Comment(rs.getString("comment"), rs.getString("date")));

				rs = DataBaseAdmin.selectDB("SELECT * FROM images WHERE skill_id = '" + skillId + "'");
				while (rs.next())
					image.add(rs.getString("image"));
				
				rs = DataBaseAdmin.selectDB("SELECT * FROM videos WHERE skill_id = '" + skillId + "'");
				while (rs.next())
					video.add(rs.getString("video"));
				
				skill = new Skill(skillId,userId,typeName,introExpr,skillLevel,times,score,warningTag,badTag,comment,image,video);
				
			} else {
				System.out.println("資料庫無此筆資料");
				return new Skill();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return skill;
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

				if (rs.next())
					skillId = rs.getInt("skill_id");

				for (String vedio : skill.getVideo())
					DataBaseAdmin.updateDB("INSERT INTO vedio VALUES('" + skillId + "','" + vedio + "')");

				for (String image : skill.getImage())
					DataBaseAdmin.updateDB("INSERT INTO image VALUES('" + skillId + "','" + image + "')");
			} else {
				System.out.println(userId + " 技能已達上限");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static public void modifySkill(Skill skill) {

		int skillId = skill.getSkillId();
		DataBaseAdmin.updateDB(
				"UPDATE skills SET intro_expr='" + skill.getIntorExpr() + "' where (skill_id = '" + skillId + "')");

		for (String vedio : skill.getVideo())
			DataBaseAdmin.updateDB("UPDATE videos SET vedio='" + vedio + "' where (skill_id = '" + skillId + "')");

		for (String image : skill.getImage())
			DataBaseAdmin.updateDB("UPDATE images SET image='" + image + "' where (skill_id = '" + skillId + "')");
	}

	static public void createFavoriteSkill(String typeName, String userId) {
		DataBaseAdmin.updateDB("INSERT INTO favorites VALUES('" + typeName + "','" + userId + "')");
	}

	static public void deleteFavoriteSkill(String typeName, String userId) {
		DataBaseAdmin
				.updateDB("DELETE FROM favorites Where user_id= '" + userId + "'AND type_name = '" + typeName + "'");
	}

	static public ArrayList<Skill> getAllSkills(String userId) {

		ArrayList<Skill> skills = new ArrayList<Skill>();
		String query = "SELECT * FROM skills WHERE user_id = '" + userId + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		try {
			while (rs.next()) {
				skills.add(findSkill(rs.getInt("skill_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return skills;
	}

	static public ArrayList<Type> getAllFavoriteSkills(String userId) {

		ArrayList<Type> favoriteTypes = new ArrayList<Type>();
		String query = "SELECT * FROM favorites WHERE user_id = '" + userId + "'";
		ResultSet rs = DataBaseAdmin.selectDB(query);

		try {
			while (rs.next())
				favoriteTypes.add(new Type(rs.getString("type_name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return favoriteTypes;
	}

	// 評斷是否黑單：在
	static public void judgeBlock(int skillId, Score score) {

	}

	// 判斷卡片是否在邀請別人
	static public boolean isSendingInvation() {

		return false;
	}

	static public void updateSkillLevel(int skillId) {
		Skill skill = findSkill(skillId);
		int totalScore = skill.getScore().calSumScore();
		int level = (totalScore >= 15) ? (totalScore - 15) / 25 + 1 : 0;

		DataBaseAdmin.updateDB("UPDATE skills SET skill_level='" + level + "' WHERE skill_id = '" + skillId + "'");
	}
}
