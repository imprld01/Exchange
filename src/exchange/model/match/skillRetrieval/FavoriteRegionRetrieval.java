package exchange.model.match.skillRetrieval;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;
import exchange.model.match.Area;
import exchange.model.match.CandidateSkill;
import exchange.model.match.regionMatrix.RealDistanceOrder;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;
import exchange.model.skill.Type;

public class FavoriteRegionRetrieval implements SkillRetrievalSet {
	
	private int limit;
	private Area[] area;
	private String user_id;
	private int nowRegionNum;
	private ArrayList<Type> favoritesSkill;
	
	public FavoriteRegionRetrieval(Area [] area, String user_id, int limit){
		
		this.limit = limit;
		this.area = area;
		this.nowRegionNum = 0;
		this.user_id = user_id;
		this.favoritesSkill = SkillManager.getAllFavoriteSkills(user_id);
	}
	
	public ArrayList<CandidateSkill> retrieveSkills(){

		if(this.nowRegionNum < RealDistanceOrder.areaQuantity()){
			
			int nowdistance = 0;
			
			ArrayList<CandidateSkill> candidates = new ArrayList<CandidateSkill>();
		
			String sql = "select skills.skill_id from skills,accounts where skills.user_id=accounts.user_id and skill_id not in(select invitations.ivt_sender from invitations where invitations.ivt_sender=skill_id) and skills.bad_tag=false and datediff(CURRENT_DATE(),accounts.recent_log) <=3 and skills.user_id!='"
					+ this.user_id + "' ";
	
			for (int i = 0; i < this.favoritesSkill.size(); i++) {
				if(i==0){
					sql = sql + "and ( ";
				}
				sql = sql + "skills.type_name='" + this.favoritesSkill.get(i).getTypeName() + "' ";
				if(i != this.favoritesSkill.size() - 1){
					sql = sql + "or ";
				}
				else{
					sql = sql + ") ";
				}
				
			}
	
			// ----------------------------------------------------------------------------------
			for (int j = this.nowRegionNum; j < RealDistanceOrder.areaQuantity(); j++) {
				if (j == this.nowRegionNum) { // 第一次
					sql = sql + "and ( ";
				}
	
				nowdistance = this.area[j].getDistance();
				sql = sql + "accounts.region='" + this.area[j].getPlaceName() + "' ";
				if (j != (RealDistanceOrder.areaQuantity() - 1) && nowdistance == this.area[j + 1].getDistance()) { // 如果下一筆距離一樣且不是最後一個地區
					sql = sql + "or ";
				} else {
					sql = sql + ") ";
					this.nowRegionNum = j + 1;
					break;
				}
			}
	
			sql = sql + " order by rand(now()) limit " + this.limit + "; ";
	
			//System.out.println("[sql]->" + sql);
	
			ResultSet result = DataBaseAdmin.selectDB(sql);
	
			try {
				while (result.next()) { // 顯示當前抓了幾筆資料 預計在這將資料存入陣列
	
					this.limit--; // 計算還需抓幾筆資料
	
					// System.out.println(result.getString("skill_id"));
	
					Skill skill = SkillManager.findSkill(result.getInt("skill_id"));
					CandidateSkill sc = new CandidateSkill(skill, nowdistance);
					candidates.add(sc);
	
					// System.out.println(sc.skill.getAccount());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// System.out.println(limit); 測試用
		
			return candidates;
		}
		
		return null;
	}
}