package exchange.model.evaluation;

import java.sql.SQLException;
import java.util.Date;

import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Score;

public class EvaluationManager{
	
	public static void saveComment(String skillId, String comment) throws SQLException
	{
		
		Date recentLog = new Date();
		java.sql.Date now = new java.sql.Date(recentLog.getTime());
		String query = "INSERT comments SET comment = '"+ comment +"',date = '"+ now +"' where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);
	}

	public static void saveScore(String skillId, Score score)
	{
		String query = "UPDATE skills SET  = '"+ score.getAttitude() +"', '"+score.getProfession()+"',where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);


	}
}
