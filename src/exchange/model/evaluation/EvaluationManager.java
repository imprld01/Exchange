package exchange.model.evaluation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Score;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;

public class EvaluationManager{
	
	public static int saveComment(int skillId, String comment) throws SQLException
	{
		int result = 0;
		Date recentLog = new Date();
		java.sql.Date now = new java.sql.Date(recentLog.getTime());
		try{
			String query = "INSERT comments INTO skill_id = '"+ skillId +"', comment = '"+ comment +"',date = '"+ now +"'";
		    result = DataBaseAdmin.updateDB(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static int saveScore(int skillId, Score score) throws SQLException
	{
		int result = 0;
		Skill skill = SkillManager.findSkill(skillId);
		try{
			String query = "UPDATE skills SET attitude_score = '"+ (skill.getScore().getFrequency() + score.getAttitude()) +"',"
			+ " profession_score = '"+ (skill.getScore().getProfession() + score.getProfession()) +"', "
			+ "teaching_score = '"+(skill.getScore().getTeaching() + score.getTeaching())+"',"
			+ " frequency_score = '"+(skill.getScore().getFrequency() + score.getFrequency()) +"', "
			+ "satisfication_score = '"+ (skill.getScore().getSatisfication() + score.getSatisfication()) +"'"
			+ "where skill_id = '"+skillId+"'";
			result = DataBaseAdmin.updateDB(query);
		}catch(Exception e){
			
		}
		return result;
	}
}
