package exchange.model.exchange;

import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.skill.Comment;
import exchange.model.skill.Score;
import exchange.model.skill.Skill;

public class MySkill extends Skill {

	private String exchangeTypeName;
	public MySkill(Skill skill , String exchangeTypeName) {
		super(skill);
		this.exchangeTypeName = exchangeTypeName;
		// TODO Auto-generated constructor stub
	}
	public String getExchangeTypeName() {
		return exchangeTypeName;
	}
	public void setExchangeTypeName(String exchangeTypeName) {
		this.exchangeTypeName = exchangeTypeName;
	}
	@Override
	public String toString() {
		if(exchangeTypeName==""){
			return "養精蓄銳中";
		}
		else{
			return "與"+this.exchangeTypeName+"技能交流中";
		}
	}

	
}
