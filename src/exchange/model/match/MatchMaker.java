package exchange.model.match;

import java.util.ArrayList;

import exchange.model.match.skillRetrieval.SkillRetrievalSet;
import exchange.model.skill.Skill;

public interface MatchMaker {
	
	public Skill toMatch();
	public void sortSkills(ArrayList<CandidateSkill> candidates);
	public ArrayList<CandidateSkill> getSkillArray(String user_id);
	public ArrayList<CandidateSkill> retrieveSkills();
	public Area [] getRegionMatrix(Region region);
	public void computeSkillScore(ArrayList<CandidateSkill> cs);
	public void computeDistanceWeight(ArrayList<CandidateSkill> cs);
	public void changeSkillRetrievalMethod(SkillRetrievalSet skillRetrieval);
	
}