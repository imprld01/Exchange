package exchange.model.match;

import exchange.model.skill.Skill;

public class CandidateSkill implements Comparable<CandidateSkill> {

	private Skill skill;						// 候選技能
	private int distance;						// 技能距離順位
	private double skillScore;						// 技能分數
	private double distanceWeight_numerator;	// 距離權重分子
	private double distanceWeight_denominator;	// 距離權重分母
	private double totalScore;					// 總分

	public CandidateSkill(Skill skill, int distance) {
		
		this.skill = skill;
		this.distance = distance;
		this.distanceWeight_numerator = 0;
		this.distanceWeight_denominator = 0;
		this.totalScore = 0;
	}
	
	public Skill getSkill() {
		
		return skill;
	}

	public void setSkill(Skill skill) {
		
		this.skill = skill;
	}

	public int getDistance() {
		
		return distance;
	}

	public void setDistance(int distance) {
		
		this.distance = distance;
	}

	public double getSkillScore() {
		
		return skillScore;
	}

	public void setSkillScore(double skillScore) {
		
		this.skillScore = skillScore;
	}

	public double getDistanceWeight_numerator() {
		
		return distanceWeight_numerator;
	}

	public void setDistanceWeight_numerator(double distanceWeight_numerator) {
		
		this.distanceWeight_numerator = distanceWeight_numerator;
	}

	public double getDistanceWeight_denominator() {
		
		return distanceWeight_denominator;
	}

	public void setDistanceWeight_denominator(double distanceWeight_denominator) {
		
		this.distanceWeight_denominator = distanceWeight_denominator;
	}

	public void calculateTotalScore() {
		
		if(skillScore == 0) this.totalScore = 0;
		this.totalScore = skillScore * (distanceWeight_numerator / distanceWeight_denominator);
	}
	
	@Override
	public int compareTo(CandidateSkill other) {
		
		return -Double.compare(this.totalScore, other.totalScore);
	}
}
