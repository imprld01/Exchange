package exchange.model.match.algorithm;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import exchange.model.account.AccountManager;
import exchange.model.match.Area;
import exchange.model.match.CandidateSkill;
import exchange.model.match.Region;
import exchange.model.match.distanceWeight.DistanceWeightSet;
import exchange.model.match.distanceWeight.NormalizationWeight;
import exchange.model.match.regionMatrix.RealDistanceOrder;
import exchange.model.match.regionMatrix.RegionMatrixSet;
import exchange.model.match.skillRetrieval.FavoriteRegionRetrieval;
import exchange.model.match.skillRetrieval.SkillRetrievalSet;
import exchange.model.match.skillScore.SkillScoreSet;
import exchange.model.match.skillScore.SumEvalScoreWithFitLvWt;
import exchange.model.skill.Skill;

public class BasicAlgorithm implements MatchMaker {

	private Queue<Skill> skillQueue;
	private RegionMatrixSet regionMatrix;
	private SkillScoreSet skillScore;
	private DistanceWeightSet distanceWeight;
	private SkillRetrievalSet skillRetrieval;
	
	private String user_id;
	private String skill_id;
	
	public BasicAlgorithm(String user_id, String skill_id){
		
		this.skillQueue = new LinkedList<Skill>();
		
		this.regionMatrix = new RealDistanceOrder();
		this.skillScore = new SumEvalScoreWithFitLvWt();
		this.distanceWeight = new NormalizationWeight();
		this.skillRetrieval = new FavoriteRegionRetrieval();
		
		this.user_id = user_id;
		this.skill_id = skill_id;
	}
	
	public BasicAlgorithm(RegionMatrixSet regionMatrix, SkillScoreSet skillScore,
			DistanceWeightSet distanceWeight, SkillRetrievalSet skillRetrieval,
			String user_id, String skill_id){
		
		this.skillQueue = new LinkedList<Skill>();
		
		this.regionMatrix = regionMatrix;
		this.skillScore = skillScore;
		this.distanceWeight = distanceWeight;
		this.skillRetrieval = skillRetrieval;
		
		this.user_id = user_id;
		this.skill_id = skill_id;
	}
	
	public Skill toMatch(){
		
		return this.skillQueue.poll();
	}
	
	public void sortSkills(){
		
		// sorting array[50].
	}
	
	public void getSkillArray(){
		
		ArrayList<CandidateSkill> skillArray = new ArrayList<CandidateSkill>();
		
		AccountManager am = new AccountManager();
		//region = ;
		//getRegionMatrix(Region region);
		// (0.5) prpare distance matrix.
		// (1) (loop) retrieve skills from db many times until array full.
		// (2) compute all skills' score.
		// (3) compute all skills' distance weight.
		// (4) call method calculateTotalScore in XXX.
		// (5) sorting the array by total score(attribute) in XXX.
		// (6) put elements in array into queue.
	}
	
	public void retrieveSkills(){
		
		this.skillRetrieval.retrieveSkills();
	}
	
	public Area [] getRegionMatrix(Region region){
		
		return this.regionMatrix.getRegionMatrix(region);
	}
	
	public void computeSkillScore(){
		
		this.skillScore.computeSkillScore();
	}
	
	public void computeDistanceWeight(){
		
		this.distanceWeight.computeDistanceWeight();
	}
	
	public void changeSkillRetrievalMethod(SkillRetrievalSet skillRetrieval){
		
		this.skillRetrieval = skillRetrieval;
	}
}