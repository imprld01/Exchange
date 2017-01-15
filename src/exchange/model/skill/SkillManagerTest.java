package exchange.model.skill;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;

public class SkillManagerTest {

	private SkillManager skillmanager;
	
	@Before
	public void setUp() throws Exception {
		DataBaseAdmin.openConnection("root", "narutoap12");
		skillmanager = new SkillManager();
	}

	@After
	public void tearDown() throws Exception {
		skillmanager = null;
	}

	@Test
	public void testFindSkill() {
		int input_skillId = 0;
		Skill input = skillmanager.findSkill(input_skillId);
	}

}
