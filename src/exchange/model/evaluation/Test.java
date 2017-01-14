package exchange.model.evaluation;

import java.sql.SQLException;

import exchange.model.database.DataBaseAdmin;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DataBaseAdmin db = new DataBaseAdmin();
		db.changeDBAccount("root", "narutoap12");		
		EvaluationManager em = new EvaluationManager();
//		em.saveComment(5313, "You are suck!");
		em.saveScore(skillId, score)
		

	}

}
