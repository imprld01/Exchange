package exchange.model.evaluation;

import java.sql.SQLException;

import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Score;
import exchange.model.skill.SkillManager;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		
		
		EvaluationManager em = new EvaluationManager();
		em.saveComment(2, "You suck!");
		
		
		em.saveScore(1, new Score(1, 1, 1, 1, 1));
		

	}

}
