package exchange.model.match;

import exchange.model.skill.Skill;

public class SkillCard implements Comparable<SkillCard> {
	private Skill skill;	
	private int distance;     //距離
	private double distanceCoefficient; //分子
	private double distanceCoefficientSum;  //分母
	private double score; //分數
	
	
	public SkillCard(Skill skill,int distance) {
		this.skill=skill;
		this.distance=distance;
		// TODO Auto-generated constructor stub
	}
	
	public void setScore(double score){
		this.score=score;
	}
	
	public void setDistanceCoefficientSum(double distanceCoefficientSum){
		this.distanceCoefficientSum=distanceCoefficientSum;
	}
	
	
	public void setDistanceCoefficient(double distanceCoefficient){
		this.distanceCoefficient=distanceCoefficient;
	}
	
	public double getDistanceCoefficientSum(){
		return this.distanceCoefficientSum;
	}
	
	
	public double getDistanceCoefficient(){
		return this.distanceCoefficient;
	}
	
	public int getDisrance(){
		return this.distance;
	}
	public double getScore(){
		return this.score;
	}
	public Skill getSkill(){
		return this.skill;
	}
	@Override
	public int compareTo(SkillCard otherSkill) {
		// TODO Auto-generated method stub
		
		return -Double.compare(this.score, otherSkill.score);
	}


	
}
