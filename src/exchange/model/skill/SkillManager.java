package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.account.AccountManager;
import exchange.model.database.DataBaseAdmin;

public class SkillManager {

	// 取得資料庫中完整技能資料
	// 接收參數:skill_id
	// 回傳型態:Skill
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

				skill = new Skill(skillId, userId, typeName, introExpr, skillLevel, times, score, warningTag, badTag,
						comment, image, video);

			} else {
				System.out.println("資料庫無此筆資料");
				return new Skill();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return skill;
	}

	// 新增技能
	// 限制每個帳號一開始只可以新增三個技能，交流後就不可刪除，且不可以重複創建同項技能。
	// 接收參數:skill
	// 回傳型態:void
	static public void createSkill(Skill skill) {
		// 判斷是否可新增技能
		int skillId = 0;
		String userId = skill.getUserId();
		String typeName = skill.getType().getTypeName();

		try {
			if (AccountManager.isSkillFull(userId)) {
				// 資料庫會自動判斷是否已存在
				/*
				 * ResultSet rs = DataBaseAdmin.
				 * selectDB("SELECT * FROM skills where user_id = '" + userId +
				 * "'"); rs.next(); if (skill.getUserId() ==
				 * rs.getString("user_id") && skill.getType().getTypeName() ==
				 * rs.getString("type_name")){ System.out.println("已建立'"+
				 * skill.getType().getTypeName() + "'類別的興趣技能"); return; }
				 */

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
				System.out.println("新增技能成功");
			} else {
				System.out.println(userId + " 技能已達上限");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 修改技能資訊
	// 接收參數:skill
	// 回傳型態:void
	static public void modifySkill(Skill skill) {

		int skillId = skill.getSkillId();
		DataBaseAdmin.updateDB(
				"UPDATE skills SET intro_expr='" + skill.getIntorExpr() + "' where (skill_id = '" + skillId + "')");

		for (String vedio : skill.getVideo())
			DataBaseAdmin.updateDB("UPDATE videos SET vedio='" + vedio + "' where (skill_id = '" + skillId + "')");

		for (String image : skill.getImage())
			DataBaseAdmin.updateDB("UPDATE images SET image='" + image + "' where (skill_id = '" + skillId + "')");
	}

	// 新增使用者感興趣的技能類別
	// 接收參數:該類別的type_name、user_id
	// 回傳型態:void
	static public void createFavoriteSkill(String typeName, String userId) {
		DataBaseAdmin.updateDB("INSERT INTO favorites VALUES('" + typeName + "','" + userId + "')");
	}

	// 刪除使用者感興趣的技能類別
	// 接收參數:該類別的type_name、user_id
	// 回傳型態:void
	static public void deleteFavoriteSkill(String typeName, String userId) {
		DataBaseAdmin
				.updateDB("DELETE FROM favorites Where user_id= '" + userId + "'AND type_name = '" + typeName + "'");
	}

	// 取得使用者所有技能
	// 接收參數:user_id
	// 回傳型態:ArrayList<Skill>
	static public ArrayList<Skill> getAllSkills(String userId) {

		ArrayList<Skill> skills = new ArrayList<Skill>();
		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM skills WHERE user_id = '" + userId + "'");

		try {
			while (rs.next()) {
				skills.add(findSkill(rs.getInt("skill_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return skills;
	}

	// 取得使用者所有感興趣的技能類別
	// 接收參數:user_id
	// 回傳型態:ArrayList<Type>
	static public ArrayList<Type> getAllFavoriteSkills(String userId) {

		ArrayList<Type> favoriteTypes = new ArrayList<Type>();
		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM favorites WHERE user_id = '" + userId + "'");

		try {
			while (rs.next())
				favoriteTypes.add(new Type(rs.getString("type_name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return favoriteTypes;
	}

	// 評斷是否黑單：
	// 連續兩次評價分數5分，封鎖三天，且累積一次警告，再連續兩次評價5分，封鎖七天，累績二次警告，累積三次警告該技能永久封鎖。
	// (現在做不到QAQ)
	// 接收參數:skill_id、score
	// 回傳型態:
	static public void judgeBlock(int skillId, Score score) {
		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM skills WHERE skill_id = '" + skillId + "'");

		int warningTimes = 0;
		int badTimes = 0;
		try {
			rs.next();
			warningTimes = rs.getInt("warning_tag");
			badTimes = rs.getInt("bad_tag");
			System.out.println("warningTimes:" + warningTimes + ", badTimes:" + badTimes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (score.calSumScore() <= 5) {
			
			if (warningTimes == 1 && badTimes == 0) {

				DataBaseAdmin.updateDB("UPDATE skills SET bad_tag='" + 1 + "' WHERE skill_id = '" + skillId + "'");
				DataBaseAdmin.updateDB("UPDATE skills SET warning_tag='0' WHERE skill_id = '" + skillId + "'");
			}else {
				DataBaseAdmin.updateDB("UPDATE skills SET warning_tag='" + (warningTimes + 1) + "' WHERE skill_id = '"
						+ skillId + "'");
			}
			
		}

	}

	// 判斷卡片是否在邀請別人
	// 邀請者:不能去配對，不能被配對
	// 受邀者:不能去配對，能被配對
	// 接收參數:skill_id
	// 回傳型態:boolean
	static public boolean isSendingInvation(int skillId) {
		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM invitations WHERE ivt_sender = '" + skillId + "'");
		try {
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 更新資料庫中的技能等級
	// 第一次升級僅需要15個評價分，之後每次升級需25個評價分。
	// 任一個技能上升5個等級就增加一個新增技能欄位。
	// 接收參數:skill_id
	// 回傳型態:void
	static public void updateSkillLevel(int skillId) {
		Skill skill = findSkill(skillId);
		int totalScore = skill.getScore().calSumScore();
		int level = (totalScore >= 15) ? (totalScore - 15) / 25 + 1 : 0;
		int newSkill = level / 5 + 3;

		DataBaseAdmin.updateDB("UPDATE skills SET skill_level='" + level + "' WHERE skill_id = '" + skillId + "'");

		ResultSet rs = DataBaseAdmin.selectDB("SELECT * FROM skills WHERE skill_id = '" + skillId + "'");

		try {
			rs.next();
			DataBaseAdmin.updateDB("UPDATE accounts SET skill_max='" + newSkill + "' WHERE user_id = '"
					+ rs.getString("user_id") + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		// 測試是否能夠成功新增技能
		SkillManager.createSkill(new Skill("bowen", "123123", "游泳", new ArrayList<String>(), new ArrayList<String>()));

		// 測試取得資料庫中完整技能資料
		System.out.println(SkillManager.findSkill(13));

		// 新增使用者感興趣的技能類別
		SkillManager.createFavoriteSkill("吉他", "vegetable");

		// 刪除使用者感興趣的技能類別
		SkillManager.deleteFavoriteSkill("吉他", "vegetable");

		// 取得使用者所有技能
		System.out.println(SkillManager.getAllSkills("vegetable"));

		// 取得使用者所有感興趣的技能類別
		System.out.println(SkillManager.getAllFavoriteSkills("vegetable"));

		// 測試修改技能資訊modifySkill
		// System.out.println(modifySkill(new skill()));

		// 測試卡片是否在邀請別人
		System.out.println(SkillManager.isSendingInvation(1));

		// 更新資料庫中的技能等級
		SkillManager.updateSkillLevel(1);

		// 評斷是否黑單
		SkillManager.judgeBlock(13, new Score());
	}

}
