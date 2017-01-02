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
	private ArrayList<String> image;
	private ArrayList<String> vedio;
	
	//建構子()
	public Skill()
	{
		skillId = 0;
		skillLv = 0;
		times = 0;
		badTag = false;
		warningTag = false;
		intorExper = null;
		type = new Type();
		score = new Score();
		comment = new ArrayList<Comment>();
		image = new ArrayList<String>();
		vedio = new ArrayList<String>();
	}
	
	//建構子()
	public Skill(int skillId,String intorExper,Type type)
	{
		this.skillId = skillId;
		this.skillLv = 0;
		times = 0;
		badTag = false;
		warningTag = false;
		this.intorExper = intorExper;
		this.type = new Type(type);
		score = new Score();
		comment = new ArrayList<Comment>();
		image = new ArrayList<String>();
		vedio = new ArrayList<String>();
	}
}
