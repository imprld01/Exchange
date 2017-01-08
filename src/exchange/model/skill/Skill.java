package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;

public class Skill {
	private int skillId;
	private String userId;
	private Type type;
	private String introExpr;
	private int skillLevel;
	private int times;
	private Score score;
	private boolean warningTag;
	private boolean badTag;
	private ArrayList<String> image;
	private ArrayList<String> video;
	private ArrayList<Comment> comment;

	// 建構子()
	public Skill() {
		skillId = 0;
		skillLevel = 0;
		times = 0;
		badTag = false;
		warningTag = false;
		introExpr = null;
		type = new Type();
		score = new Score();
		comment = new ArrayList<Comment>();
		image = new ArrayList<String>();
		video = new ArrayList<String>();
	}

	// 建構子(全參數)12個
	public Skill(int skillId, String userId, String typeName, String introExpr, int skillLevel, int times, Score score,
			boolean badTag, boolean warningTag, ArrayList<Comment> comment, ArrayList<String> image,
			ArrayList<String> video) {
		this.skillId = skillId;
		this.userId = userId;
		this.type = new Type(typeName);
		this.introExpr = introExpr;
		this.skillLevel = skillLevel;
		this.times = times;
		this.warningTag = warningTag;
		this.badTag = badTag;
		this.score = score;
		this.comment = comment;
		this.image = image;
		this.video = video;
	}

	// 建構子() 用於新增技能
	public Skill(String userId, String intorExper, String typeName, ArrayList<String> image, ArrayList<String> video) {
		this(0, userId, typeName, intorExper, 0, 0, new Score(), false, false, null, image, video);
		// this.userId = userId;
		// this.introExpr = new String(intorExper);
		// this.type = new Type(typeName);
		// this.image = new ArrayList<String>(image);
		// this.video = new ArrayList<String>(video);
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
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
		return introExpr;
	}

	public void setIntorExpr(String introExpr) {
		this.introExpr = introExpr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public ArrayList<String> getVideo() {
		return video;
	}

	public void setVideo(ArrayList<String> video) {
		this.video = video;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public double calAvgScore() {

		return score.calSumScore() / times;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", userId=" + userId + ", type=" + type + ", introExpr=" + introExpr
				+ ", skillLevel=" + skillLevel + ", times=" + times + ", score=" + score + ", warningTag=" + warningTag
				+ ", badTag=" + badTag + ", image=" + image + ", video=" + video + ", comment=" + comment + "]";
	}
}
