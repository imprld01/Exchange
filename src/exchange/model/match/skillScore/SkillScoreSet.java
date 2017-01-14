package exchange.model.match.skillScore;

import java.util.ArrayList;

import exchange.model.match.CandidateSkill;

public interface SkillScoreSet {

	public void computeSkillScore(ArrayList<CandidateSkill> cs);
}