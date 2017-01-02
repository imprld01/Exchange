package exchange.model.skill;

import java.util.ArrayList;

public class Skill {
	private int skillId;
	private int skillLv;
	private int times;
	private boolean badTag;
	private boolean warningTag;
	private String intorExper;
	private Type type;
	private Score score;
	private ArrayList<Comment> comment;
	
	public Skill(int skillId,String intorExper,Type type)
	{
		
	}
}
