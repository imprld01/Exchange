package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;

public class Skill {
	private int skillId;
	private int skillLv;
	private int times;
	private boolean badTag;
	private boolean warningTag;
	private String intorExpr;
	private String account;
	private Type type;
	private Score score;
	private ArrayList<Comment> comment;
	private ArrayList<String> image;
	private ArrayList<String> vedio;

	// 建構子()
	public Skill() {
		skillId = 0;
		skillLv = 0;
		times = 0;
		badTag = false;
		warningTag = false;
		intorExpr = null;
		type = new Type();
		score = new Score();
		comment = new ArrayList<Comment>();
		image = new ArrayList<String>();
		vedio = new ArrayList<String>();
	}

	// 建構子()
	public Skill(int skillId) {
		this.skillId = skillId;

		
		try {
			String query = "SELECT * FROM skills WHERE skill_id = '" + this.skillId + "' ";
			ResultSet rs = DataBaseAdmin.selectDB(query);
			String type_name = new String();
			
			if (rs.next()) {
				times = rs.getInt("times");
				skillLv = rs.getInt("skill_level");
				times = rs.getInt("times");
				score = new Score(rs.getInt("attitude_score"),rs.getInt("profession_score"),
						rs.getInt("teaching_score"),rs.getInt("frequency_score"),rs.getInt("satisfication_score"));
				badTag = (rs.getBoolean("bad_tag")) ? true : false;
				warningTag = (rs.getBoolean("warning_tag")) ? true : false;
				intorExpr = rs.getString("intro_expr");
				type_name = rs.getString("type_name");
				account = rs.getString("account");	
			}
			
			query = "SELECT * FROM types WHERE type_name = '" + type_name + "'";
			rs = DataBaseAdmin.selectDB(query);
			if (rs.next()) {
				type = new Type(new Code(rs.getString("type_code")),
						rs.getString("type_name"),
						new Code(rs.getString("class_code")));
			}
			
			
			comment = new ArrayList<Comment>();
			image = new ArrayList<String>();
			vedio = new ArrayList<String>();
			
			query = "SELECT * FROM comments WHERE skill_id = '" + this.skillId + "'";
			rs = DataBaseAdmin.selectDB(query);
			while (rs.next()) {
				comment.add(new Comment(rs.getString("comment"),rs.getString("date")));
			}
			
			query = "SELECT * FROM images WHERE skill_id = '" + this.skillId + "'";
			rs = DataBaseAdmin.selectDB(query);
			while (rs.next()) {
				image.add(rs.getString("image"));
			}
			
			query = "SELECT * FROM videos WHERE skill_id = '" + this.skillId + "'";
			rs = DataBaseAdmin.selectDB(query);
			while (rs.next()) {
				vedio.add(rs.getString("video"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	// 建構子(int,String,Type,image,vedio) 用於新增技能
	public Skill(int skillId, String intorExper, Type type, ArrayList<String> image, ArrayList<String> vedio) {
		this.skillId = skillId;
		times = 0;
		badTag = false;
		warningTag = false;
		this.intorExpr = new String(intorExper);
		this.type = new Type(type);
		score = new Score();
		this.skillLv = 0;
		comment = new ArrayList<Comment>();
		this.image = new ArrayList<String>(image);
		this.vedio = new ArrayList<String>(vedio);
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getSkillLv() {
		return skillLv;
	}

	public void setSkillLv(int skillLv) {
		this.skillLv = skillLv;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public boolean isBadTag() {
		return badTag;
	}

	public void setBadTag(boolean badTag) {
		this.badTag = badTag;
	}

	public boolean isWarningTag() {
		return warningTag;
	}

	public void setWarningTag(boolean warningTag) {
		this.warningTag = warningTag;
	}

	public String getIntorExpr() {
		return intorExpr;
	}

	public void setIntorExpr(String intorExpr) {
		this.intorExpr = intorExpr;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public ArrayList<Comment> getComment() {
		return comment;
	}

	public void setComment(ArrayList<Comment> comment) {
		this.comment = comment;
	}

	public ArrayList<String> getImage() {
		return image;
	}

	public void setImage(ArrayList<String> image) {
		this.image = image;
	}

	public ArrayList<String> getVedio() {
		return vedio;
	}

	public void setVedio(ArrayList<String> vedio) {
		this.vedio = vedio;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType()
	{
		return type;
	}
	
	public void calSkillLevel() {

		try {
			skillLv = score.calSumScore() / times;
		} catch (ArithmeticException ae) {
			skillLv = 0;
		}
	}

	public double calAvgScore() {

		return score.calSumScore() / 5;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillLv=" + skillLv + ", times=" + times + ", badTag=" + badTag
				+ ", warningTag=" + warningTag + ", intorExpr=" + intorExpr + ", account=" + account + ", type=" + type
				+ ", score=" + score + ", comment=" + comment + ", image=" + image + ", vedio=" + vedio + "]";
	}
}
