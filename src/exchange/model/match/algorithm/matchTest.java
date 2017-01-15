package exchange.model.match.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exchange.model.database.DataBaseAdmin;
import exchange.model.match.MatchMaker;
import exchange.model.skill.Skill;

public class matchTest {
	MatchMaker ba;
	double distanceCoefficient[]={4,3,3,2}; //分子
	double distanceCoefficientSum=12;  //分母
	double score[]={11.3,10.5,6.75,0};
	Queue<Skill> qs= new LinkedList<Skill>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void sortTest() {
			DataBaseAdmin.changeDBAccount("root", "a2n5h011oj");
		 ba = new BasicAlgorithm("vegetable",1);
		 
		
			
	}

}
