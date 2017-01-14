package exchange.model.match.algorithm;

import exchange.model.skill.Skill;

public interface MatchMaker {
	
	public Skill toMatch();
	public void getSkillArray();
}