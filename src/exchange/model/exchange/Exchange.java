package exchange.model.exchange;

import exchange.model.skill.Skill;

public class Exchange {
	public static final int IDLE = 0;
	public static final int EXCHANGING = 1;
	public static final int UNFINISH_EXCHANGE = 2;
	public static final int SENDING_INVITATION = 3;
	public static final int RECEIVING_INVITATION = 4;
	
	private int status;
	
	private Skill othersSkill;
	private Skill mySkill;

	public Exchange(Skill mySkill,Skill othersSkill){
		
		this.mySkill = mySkill;
		this.othersSkill = othersSkill;
		status = EXCHANGING;
	}
	public Exchange(Skill mySkill,Skill othersSkill,int status){
		this.mySkill = mySkill;
		this.othersSkill = othersSkill;
		this.status = status;
	}
	public Exchange(Skill mySkill, int status){
		this.mySkill = mySkill;
		this.status = status;
		this.othersSkill = new Skill();
	}
	public String mySkillStatus(){
		String statusString;
		switch(status){
		case IDLE:
			statusString =  "養精蓄銳中";
			break;
		case EXCHANGING:
			statusString = "與"+othersSkill.getType().getTypeName()+"技能交流中";
			break;
		case UNFINISH_EXCHANGE:
			statusString = "請進行評分";
			break;	
		case SENDING_INVITATION:
			statusString =  "送出邀請中";
			break;
		case RECEIVING_INVITATION:
			statusString = "受到邀請中";
			break;
		default:
			statusString = "養精蓄銳中";
			break;
		}
		return statusString;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Exchange [status=" + status + ", othersSkill=" + othersSkill + ", mySkill=" + mySkill + "]";
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

