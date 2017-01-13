package exchange.model.exchange;

import exchange.model.skill.Skill;

public class Exchange {
	private Skill othersSkill;
	private Skill mySkill;
	public Exchange(Skill mySkill,Skill othersSkill){
		this.mySkill = mySkill;
		this.othersSkill = othersSkill;
	}
	@Override
	public String toString() {
		return othersSkill.getType().getTypeName();
	}
	public Skill getOthersSkill() {
		return othersSkill;
	}
	public void setOthersSkill(Skill othersSkill) {
		this.othersSkill = othersSkill;
	}
	public Skill getMySkill() {
		return mySkill;
	}
	public void setMySkill(Skill mySkill) {
		this.mySkill = mySkill;
	}

}
