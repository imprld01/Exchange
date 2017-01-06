package exchange.model.evaluation;

import java.sql.SQLException;
import java.util.Date;

import exchange.model.database.DataBaseAdmin;

public class EvaluationManager{
	String skillId;
	String comment;
	
	public void saveComment() throws SQLException
	{
		
		Date recentLog = new Date();
		java.sql.Date now = new java.sql.Date(recentLog.getTime());
		String query = "INSERT comments SET comment = '"+ comment +"' where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);
	}

	int attitude;
	int profession;
	int teaching;
	int frequency;
	int satisfication;

	public void saveScore()
	{
<<<<<<< HEAD
		String query = "UPDATE skills SET  = '"+ attitude +"', '"+ profession +"',"
				+ " '"+ teaching +"', '"+ frequency +"', '"+ satisfication +"' where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);
=======
		String query = "UPDATE skills SET  = '"+ attitude +"', '"+ profession +"',  where skill_id = '"+skillId+"'";
		

>>>>>>> 0513981648e797c587793d381a292b539d102cda
	}
}
