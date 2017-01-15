package exchange.model.match.algorithm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;
import exchange.model.match.CandidateSkill;
import exchange.model.match.MatchMaker;
import exchange.model.skill.Skill;

public class matchTest {
	BasicAlgorithm ba;
	double distanceCoefficient[]={4,3,3,2}; //分子
	double distanceCoefficientSum=12;  //分母
	double score[]={11.3,10.5,6.75,0};
	ArrayList<CandidateSkill> cs;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		DataBaseAdmin.changeDBAccount("root", "a2n5h011oj");
	}

	@After
	public void tearDown() throws Exception {
	}

	
	private void assertSortSuccess(ArrayList<CandidateSkill> cs){ //測試排序有無錯誤
		
		for(int i=0;i<cs.size()-1;i++){
			//System.out.println(cs.get(i).getSkillScore());
			//System.out.println(cs.get(i+1).getSkillScore());
			boolean	larger=(cs.get(i).getTotalScore()>=cs.get(i+1).getTotalScore());		
			assertTrue(larger);
		}
		
	}
	
	
	@Test
	public void sortTest() {
			
			ba = new BasicAlgorithm("vegetable",1);
			cs=ba.getSkillArray("vegetable");
			assertSortSuccess(cs);
		
			
	}
	
	@Test
	public void stressTest() {
		
		ba=new BasicAlgorithm("vegetable", 1);
		ba.getSkillArray("vegetable");
		
		int queueSize=ba.getSkillQueue().size();
		for(int i=0;i<=queueSize ;i++){
			
			
			Skill skill;
			skill=ba.toMatch();
			if(i!=queueSize){
				assertNotEquals(skill, null);
			}
			else{
				assertEquals(skill, null);
			}
		}
		
	}
	
	@Test
	public void distanceCoefficientTest(){
		ba=new BasicAlgorithm("vegetable", 1);
		cs=ba.getSkillArray("vegetable");
		
		
		
		for(int i=0;i<cs.size();i++){
			System.out.println(cs.get(i).getSkill());
			//assertEquals(cs.get(i).getDistanceWeight_numerator(), distanceCoefficient[i],0);
			//assertEquals(cs.get(i).getDistanceWeight_denominator(),distanceCoefficientSum,0);
		}
	}
	
	

}
