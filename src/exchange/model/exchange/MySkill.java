package exchange.model.exchange;

import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.skill.Comment;
import exchange.model.skill.Score;
import exchange.model.skill.Skill;

public class MySkill extends Skill {

	public MySkill() {
		// TODO Auto-generated constructor stub
	}

	public MySkill(int skillId, String userId, String typeName, String introExpr, int skillLevel, int times,
			Score score, boolean badTag, boolean warningTag, ArrayList<Comment> comment, ArrayList<String> image,
			ArrayList<String> video) {
		super(skillId, userId, typeName, introExpr, skillLevel, times, score, badTag, warningTag, comment, image,
				video);
		// TODO Auto-generated constructor stub
	}

	public MySkill(String userId, String intorExper, String typeName, ArrayList<String> image, ArrayList<String> video)
			throws SQLException {
		super(userId, intorExper, typeName, image, video);
		// TODO Auto-generated constructor stub
	}

}
