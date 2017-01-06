package exchange.model.evaluation;

import java.sql.SQLException;
import exchange.model.database.DataBaseAdmin;

public class EvaluationManager{
	String skillId;
	String comment;
	
	public void saveComment() throws SQLException
	{
		String query = "UPDATE comments SET comment = '"+ comment +"' where skill_id = '"+skillId+"'";
		DataBaseAdmin.updateDB(query);
	}

	int attitude;
	int profession;
	int teaching;
	int frequency;
	int satisfication;

	public void saveScore()
	{
		String query = "UPDATE skills SET  = '"+ attitude +"', '"+ profession +"', "''" where skill_id = '"+skillId+"'";
		
		
		
	}
}
