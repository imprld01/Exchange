package exchange.model.match.distanceWeight;

import java.util.ArrayList;

import exchange.model.match.CandidateSkill;

public interface DistanceWeightSet {

	public void computeDistanceWeight(ArrayList<CandidateSkill> cs);
}