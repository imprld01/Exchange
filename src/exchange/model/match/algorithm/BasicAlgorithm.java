package exchange.model.match.algorithm;
import java.util.ArrayList;
import java.util.Collections;
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
	private final static int sizeLimitation = 50;
	
	public BasicAlgorithm(String user_id, int skill_id){
		
		this.skillQueue = new LinkedList<Skill>();
		
		// (1) prpare distance matrix.
		AccountManager am = new AccountManager();

	
		this.regionMatrix = new RealDistanceOrder();   //原順序錯誤  無建立物件下行將無法執行 已修改
		Area [] area = this.getRegionMatrix(am.toRegionObj(am.getRegion(user_id)));  //取得地區陣列

		this.skillScore = new SumEvalScoreWithFitLvWt(user_id, skill_id);
		this.distanceWeight = new NormalizationWeight();
		this.skillRetrieval = new FavoriteRegionRetrieval(area, user_id);
		getSkillArray();
		
		for(int i=0;i<skillQueue.size();i++){
			
		}
	}
	
	public BasicAlgorithm(RegionMatrixSet regionMatrix, SkillScoreSet skillScore,
			DistanceWeightSet distanceWeight, SkillRetrievalSet skillRetrieval,
			String user_id, int skill_id){
		
		this.skillQueue = new LinkedList<Skill>();
		
		this.regionMatrix = regionMatrix; 
		this.skillScore = skillScore;
		this.distanceWeight = distanceWeight;
		this.skillRetrieval = skillRetrieval;
	}
	
	public Skill toMatch(){
		
		return this.skillQueue.poll();
	}
	
	public void sortSkills(ArrayList<CandidateSkill> candidates){
		
		Collections.sort(candidates);
	}
	
	public void getSkillArray(){
		
		// (0) declare one arrayList which type is CandidateSkill.
		ArrayList<CandidateSkill> skillArray = new ArrayList<CandidateSkill>();
		
		// (2) (loop) retrieve skills from db many times until array full.
		//do {
			ArrayList<CandidateSkill> round = this.retrieveSkills();
			for(CandidateSkill cs : round) skillArray.add(cs);
		//} while (skillArray.size() < sizeLimitation); //這裡有問題 應該不需要while 否則會一直抓重複地到結束 不然就要改裡面的dowhile 改掉
		
		// (3) compute all skills' score.
		this.computeSkillScore(skillArray);
		
		// (4) compute all skills' distance weight.
		this.computeDistanceWeight(skillArray);
			
		// (5) call method calculateTotalScore in CandidateSkill.
		for(CandidateSkill cs : skillArray) cs.calculateTotalScore();
		
		// (6) sorting the array by total score(attribute) in CandidateSkill.
		this.sortSkills(skillArray);
		
		// (7) put elements in array into queue.
		for(CandidateSkill cs : skillArray) skillQueue.add(cs.getSkill());
	}
	
	public ArrayList<CandidateSkill> retrieveSkills(){
		
		return this.skillRetrieval.retrieveSkills();
	}
	
	public Area [] getRegionMatrix(Region region){
		
		//System.out.println(this.regionMatrix.getRegionMatrix(region)[1]);

		return this.regionMatrix.getRegionMatrix(region); //似乎執行不到
	}
	
	public void computeSkillScore(ArrayList<CandidateSkill> cs){
		
		this.skillScore.computeSkillScore(cs);
	}
	
	public void computeDistanceWeight(ArrayList<CandidateSkill> cs){
		
		this.distanceWeight.computeDistanceWeight(cs);
	}
	
	public void changeSkillRetrievalMethod(SkillRetrievalSet skillRetrieval){
		
		this.skillRetrieval = skillRetrieval;
	}
}