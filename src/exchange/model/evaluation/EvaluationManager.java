package exchange.model.evaluation;

import java.util.Date;

import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Comment;

public class EvaluationManager {
	public void saveComment()
	{
		EvaluationManager e = new EvaluationManager();
		Comment c = new Comment();
		String query = "INSERT INTO comments VALUES ('skill_id','"+comment+"'," + now + " ')";
		DataBaseAdmin.updateDB(query);
	}

}
