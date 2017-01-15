package exchange.model.skill;

import static org.junit.Assert.*;

import java.util.ArrayList;

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

	@Test //測試取得技能
	public void testFindSkill() {
		int input_skillId = 1;
		String input = skillmanager.findSkill(input_skillId).toString();
		String a = "@URL";String b = "@URL_1";String c = "@URL_2";
		ArrayList<String> image = new ArrayList<String>();
		image.add(a);image.add(b);
		ArrayList<String> video = new ArrayList<String>();
		video.add(a);video.add(b);video.add(c);
		ArrayList<Comment> comment = new ArrayList<Comment>();
		String d = "You are suck!"; String e = "2017-01-15";
		Comment testComment =new Comment(d,e);
		comment.add(testComment);
		String output = new Skill(1, "vegetable", "吉他", "施博文是87", 57, 87, 
				new Score(258, 442, 360, 258, 120), false, true, comment,image, video).toString();
//		System.out.println(input);
//		System.out.println(output);
		assertEquals(input, output);
	}

	@Test //測試技能未達到上限，技能建立成功
	public void testCreateSkill1() {
		String a = "www.youtube.com";
		ArrayList<String> image = new ArrayList<String>();
		image.add(a);
		ArrayList<String> video = new ArrayList<String>();
		video.add(a);
		boolean input = skillmanager.createSkill(new Skill("test1023", "我想回家ˊˋ", "吉他", image, video));
		boolean output = true; 
		assertEquals(input, output);
	}
	
	@Test //測試技能達到上限，技能建立失敗
	public void testCreateSkill() {
		String a = "www.youtube.com";
		ArrayList<String> image = new ArrayList<String>();
		image.add(a);
		ArrayList<String> video = new ArrayList<String>();
		video.add(a);
		boolean input = skillmanager.createSkill(new Skill("10567026", "我想回家ˊˋ", "吉他", image, video));
		boolean output = false; 
		assertEquals(input, output);
	}
	
	@Test //測試技能重複
	public void testCheckSkillDuplicate1() {
		boolean input = skillmanager.checkSkillDuplicate((new Skill("vegetable", "吉他")));
		boolean output = false; 
		assertEquals(input, output);
	}
	
	@Test //測試技能沒有重複
	public void testCheckSkillDuplicate2() {
		boolean input = skillmanager.checkSkillDuplicate((new Skill("vegetable", "鋼琴")));
		boolean output = true; 
		assertEquals(input, output);
	}
	
	@Test //測試修改技能成功
	public void testModifySkill1() {
		boolean input = skillmanager.modifySkill((new Skill("vegetable", "吉他")));
		boolean output = true; 
		System.out.println(input);
		assertEquals(input, output);
	}
	
}
