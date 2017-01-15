package exchange.model.evaluation;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;
import exchange.model.sign.SignManager;
import exchange.model.skill.Score;

public class EvaluationManagerTest {
	
	private EvaluationManager evaluationmanager;
	
	@Before
	public void setUp() throws Exception {
		evaluationmanager = new EvaluationManager();
	}

	@After
	public void tearDown() throws Exception {
		evaluationmanager = null;
	}

	@Test //輸入正常SKILL id
	public void testSaveComment1() throws SQLException {
		EvaluationManager em = new EvaluationManager();
		int output = 1;
		int input = em.saveComment(5313, "You suck!");
		assertEquals(input, output);
	}

	@Test  //輸入存在的skillid測試有沒有存到
	public void testSaveScore1() throws SQLException {
		EvaluationManager em = new EvaluationManager();
		int output = 1;
		int input = em.saveScore(1, new Score(1, 1, 1, 1, 1));
		assertEquals(input, output);
	}

	@Test  //輸入不存在的skillid測試有沒有存到
	public void testSaveScore2() throws SQLException {
		EvaluationManager em = new EvaluationManager();
		int output = 0;
		int input = em.saveScore(16565, new Score(1, 1, 1, 1, 1));
		assertEquals(input, output);
	}
	
}
