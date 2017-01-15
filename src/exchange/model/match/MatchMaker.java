package exchange.model.match;

import exchange.model.skill.Skill;

public interface MatchMaker {
	
	public Skill toMatch();
}