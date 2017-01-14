package exchange.model.match.algorithm;

import exchange.model.match.Area;
import exchange.model.match.Region;
import exchange.model.match.skillRetrieval.SkillRetrievalSet;
import exchange.model.skill.Skill;

public interface MatchMaker {
	
	public Skill toMatch();
	public void sortSkills();
	public void getSkillArray();
	public void retrieveSkills();
	public Area[] getRegionMatrix(Region region);
	public void computeSkillScore();
	public void computeDistanceWeight();
	public void changeSkillRetrievalMethod(SkillRetrievalSet skillRetrieval);
}