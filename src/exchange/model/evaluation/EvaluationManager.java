package exchange.model.evaluation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Score;
import exchange.model.skill.Skill;

public class EvaluationManager{
	
	public static void saveComment(int skillId, String comment) throws SQLException
	{
		
		Date recentLog = new Date();
		java.sql.Date now = new java.sql.Date(recentLog.getTime());
		String query = "INSERT comments SET comment = '"+ comment +"',date = '"+ now +"' where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);
	}

	public static void saveScore(int skillId, Score score) throws SQLException
	{
		Skill skill = new Skill(skillId);
		String query = "UPDATE skills SET attitude_score = '"+ (skill.getScore().getFrequency() + score.getAttitude()) +"',"
				+ " profession_score = '"+ (skill.getScore().getProfession() + score.getProfession()) +"', "
				+ "teaching_score = '"+(skill.getScore().getTeaching() + score.getTeaching())+"',"
				+ " frequency_score = '"+(skill.getScore().getFrequency() + score.getFrequency()) +"', "
				+ "satisfication_score = '"+ (skill.getScore().getSatisfication() + score.getSatisfication()) +"'"
				+ "where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);
	}
}
