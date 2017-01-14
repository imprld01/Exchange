package exchange.model.match.algorithm;
import java.util.LinkedList;
import java.util.Queue;

import exchange.model.match.Region;
import exchange.model.match.distanceWeight.DistanceWeightSet;
import exchange.model.match.regionMatrix.RegionMatrixSet;
import exchange.model.match.skillRetrieval.SkillRetrievalSet;
import exchange.model.match.skillScore.SkillScoreSet;

public class BasicAlgorithm implements MatchMaker {

	private Queue<String> skillArray;
	private RegionMatrixSet regionMatrix;
	private SkillScoreSet skillScore;
	private DistanceWeightSet distanceWeight;
	private SkillRetrievalSet skillRetrieval;
	
	public BasicAlgorithm(RegionMatrixSet regionMatrix, SkillScoreSet skillScore,
			DistanceWeightSet distanceWeight, SkillRetrievalSet skillRetrieval){
		
		this.regionMatrix = regionMatrix;
		this.skillScore = skillScore;
		this.distanceWeight = distanceWeight;
		this.skillRetrieval = skillRetrieval;
		skillArray = new LinkedList<String>();
	}
	
	public void toMatch(){
		
		// pop one skill out from queue[50].
	}
	
	public void sortSkills(){
		
		// sorting array[50].
	}
	
	public void getSkillArray(){
		
		// complex instruction here:
		// (0) declare one arrayList which type is XXX.
		// (1) (loop) retrieve skills from db many times until array full.
		// (2) compute all skills' score.
		// (3) compute all skills' distance weight.
		// (4) call method calculateTotalScore in XXX.
		// (5) sorting the array by total score(attribute) in XXX.
		// (6) put elements in array into queue.
	}
	
	public void retrieveSkills(){
		
		skillRetrieval.retrieveSkills();
	}
	
	public void getRegionMatrix(Region region){
		
		regionMatrix.getRegionMatrix(region);
	}
	
	public void computeSkillScore(){
		
		skillScore.computeSkillScore();
	}
	
	public void computeDistanceWeight(){
		
		distanceWeight.computeDistanceWeight();
	}
	
	public void changeSkillRetrievalMethod(SkillRetrievalSet skillRetrieval){
		
		this.skillRetrieval = skillRetrieval;
	}
}