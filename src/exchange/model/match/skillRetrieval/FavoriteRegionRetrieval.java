package exchange.model.match.skillRetrieval;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;
import exchange.model.match.Area;
import exchange.model.match.CandidateSkill;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;
import exchange.model.skill.Type;

public class FavoriteRegionRetrieval implements SkillRetrievalSet {

	private String user_id;
	private Area[] area;
	private ArrayList<Type> favoritesSkill;
	
	public FavoriteRegionRetrieval(Area [] area, String user_id){
		
		this.user_id = user_id;
		this.area = area;
		this.favoritesSkill = SkillManager.getAllFavoriteSkills(user_id);
	}
	
	public ArrayList<CandidateSkill> retrieveSkills(){
		
		ArrayList<CandidateSkill> candidates = new ArrayList<CandidateSkill>();
		
		int nowdistance = 0;
		int nowRegionNum = 0;
		int limit = 50;
		do {

			String sql = "select skills.skill_id from skills,accounts where skills.user_id=accounts.user_id and skill_id not in(select invitations.ivt_sender from invitations where invitations.ivt_sender=skill_id) and skills.bad_tag=false and datediff(CURRENT_DATE(),accounts.recent_log) <=3 and skills.user_id!='"
					+ user_id + "' ";

			for (int i = 0; i < favoritesSkill.size(); i++) { // 加入興趣技能

				if (favoritesSkill.size() > 1) {
					if (i == 0) {
						sql = sql + "and (skills.type_name='" + favoritesSkill.get(i).getTypeName() + "' "; // 多個興趣開頭+小括號
					} else if (i == favoritesSkill.size() - 1) {
						sql = sql + "or skills.type_name='" + favoritesSkill.get(i).getTypeName() + "') ";// 多個興趣結尾+小括號
					} else {
						sql = sql + "or skills.type_name='" + favoritesSkill.get(i).getTypeName() + "' ";
					}
				} else {
					sql = sql + "and skills.type_name='" + favoritesSkill.get(i).getTypeName() + "' ";
				}
			}

			// ----------------------------------------------------------------------------------
			for (int j = nowRegionNum; j < 16; j++) {
				if (j == nowRegionNum) { // 第一次
					sql = sql + "and ( ";
				}

				nowdistance = area[j].getDistance();
				sql = sql + "accounts.region='" + area[j].getPlaceName() + "' ";
				if (j != 15 && nowdistance == area[j + 1].getDistance()) { // 如果下一筆距離一樣且不是最後一個地區
					sql = sql + "or ";
				} else {
					sql = sql + ") ";
					nowRegionNum = j + 1;
					break;
				}
			}

			sql = sql + " order by rand(now()) limit " + limit + "; ";

			//System.out.println("[sql]->" + sql);

			ResultSet result = DataBaseAdmin.selectDB(sql);

			try {
				while (result.next()) { // 顯示當前抓了幾筆資料 預計在這將資料存入陣列

					limit--; // 計算還需抓幾筆資料

					// System.out.println(result.getString("skill_id"));

					Skill skill = SkillManager.findSkill(result.getInt("skill_id"));
					int distance = nowdistance;
					CandidateSkill sc = new CandidateSkill(skill, distance);
					candidates.add(sc);

					// System.out.println(sc.skill.getAccount());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println(limit); 測試用

		} while (nowRegionNum <= 15); // 抓到50筆技能或全部地區資料抓完後跳出
		
		return candidates;
	}
}