package exchange.model.match.distanceWeight;

import java.util.ArrayList;

import exchange.model.match.CandidateSkill;

public class NormalizationWeight implements DistanceWeightSet {

	public NormalizationWeight(){
		
	}
	
	public void computeDistanceWeight(ArrayList<CandidateSkill> cs){
		
		int distancedifference = 0; // 距離差
		int distanceCoefficient = 0; // 距離係數
		int distanceCoefficientSum = 0; // 距離係數總和
		for (int i = 0; i < cs.size(); i++) { // 計算距離係數
			distanceCoefficient = cs.size() - distancedifference;
			distanceCoefficientSum += distanceCoefficient; // 累加距離係數
			cs.get(i).setDistanceWeight_numerator(distanceCoefficient);

			if (i != cs.size() - 1 && cs.get(i).getDistance() != cs.get(i + 1).getDistance()) { // 如果下一個技能距離不同
				distancedifference++;
			}

		}

		for (int i = 0; i < cs.size(); i++) {
			cs.get(i).setDistanceWeight_denominator(distanceCoefficientSum);
		}
	}
}