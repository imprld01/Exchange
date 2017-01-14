package exchange.model.evaluation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;
import exchange.model.sign.SignManager;

public class EvaluationManagerTest {
	
	private EvaluationManager evaluationmanager;
	
	@Before
	public void setUp() throws Exception {
		DataBaseAdmin.openConnection("root", "narutoap12");
		evaluationmanager = new EvaluationManager();
	}

	@After
	public void tearDown() throws Exception {
		evaluationmanager = null;
	}

	@Test
	public void test() {
		fail("Not yet implemented");
		
	}
	
	@Test
	public void testSaveComment() {
		fail("Not yet implemented");
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
		
	}

}
