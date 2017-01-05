package exchange.model.exchange;

import exchange.model.skill.Skill;

public class Exchange {
	private Skill receiveSkill;
	private String yourSkillName;
	@Override
	public String toString() {
		return "receiveSkill.getType().getTypeName()";
	}
	public Skill getReceiveSkill() {
		return receiveSkill;
	}
	public void setReceiveSkill(Skill receiveSkill) {
		this.receiveSkill = receiveSkill;
	}
	public String getYourSkillName() {
		return yourSkillName;
	}
	public void setYourSkillName(String yourSkillName) {
		this.yourSkillName = yourSkillName;
	}
	public Exchange(Skill receiveSkill, String yourSkillName) {
		this.receiveSkill = receiveSkill;
		this.yourSkillName = yourSkillName;
	}
	
}
