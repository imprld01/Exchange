package exchange.model.match.algorithm;

import exchange.model.match.Region;
import exchange.model.match.skillRetrieval.SkillRetrievalSet;

public interface MatchMaker {
	
	public void toMatch();
	public void sortSkills();
	public void getSkillArray();
	public void retrieveSkills();
	public void getRegionMatrix(Region region);
	public void computeSkillScore();
	public void computeDistanceWeight();
	public void changeSkillRetrievalMethod(SkillRetrievalSet skillRetrieval);
}