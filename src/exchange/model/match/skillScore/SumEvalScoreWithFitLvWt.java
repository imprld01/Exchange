package exchange.model.match.skillScore;

import java.util.ArrayList;

import exchange.model.match.CandidateSkill;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;

public class SumEvalScoreWithFitLvWt implements SkillScoreSet {
	
	private ArrayList<Skill> myAllSkill;
	private Skill mySkill;
	
	public SumEvalScoreWithFitLvWt(String user_id, int skill_id){
		
		this.myAllSkill = SkillManager.getAllSkills(user_id);
		this.mySkill = SkillManager.findSkill(skill_id);
	}
	
	public void computeSkillScore(ArrayList<CandidateSkill> cs){
		
		for (int i = 0; i < cs.size(); i++) {
			boolean hasSkill = false;
			// ----------Weights--------------
			double attitudeWeights;
			double professionWeights;
			double teachingWeights;
			double frequencyWeights;
			double satisficationWeights;
			double score;
			// ------------計算權重-------------------
			if (mySkill.getTimes() != 0) { // 如果為新卡片
				attitudeWeights = mySkill.getScore().getAttitude() / mySkill.getTimes(); // 教學態度權重
				satisficationWeights = mySkill.getScore().getSatisfication() / mySkill.getTimes();// 整體滿意度權重
				if (mySkill.getSkillLevel() <= 5) {
					frequencyWeights = 3;
				} // 教學頻率權重
				else {
					frequencyWeights = 1;
				}

			} else { // 如果為舊卡片
				attitudeWeights = 3;
				frequencyWeights = 3;
				satisficationWeights = 3;
			}

			for (int j = 0; j < myAllSkill.size(); j++) { // 判斷自己是否有該技能(程度與技巧權重)
				if (cs.get(i).getSkill().getType().getTypeName()
						.equals(myAllSkill.get(j).getType().getTypeName())) {
					hasSkill = true;
					break;
				}
			}

			if (hasSkill) { // 有該技能
				professionWeights = 4;
				teachingWeights = 2;
			} else { // 沒有該技能
				professionWeights = 2;
				teachingWeights = 4;
			}
			// ---------------------------------------
			// --------------計算分數-----------------
			int time = cs.get(i).getSkill().getTimes();
			if (time == 0) {
				score = 0;
			} else {
				score = cs.get(i).getSkill().getScore().getAttitude() / time * attitudeWeights +
						cs.get(i).getSkill().getScore().getProfession() / time * professionWeights +
						cs.get(i).getSkill().getScore().getTeaching() / time * teachingWeights +
						cs.get(i).getSkill().getScore().getFrequency() / time * frequencyWeights+
						cs.get(i).getSkill().getScore().getSatisfication() / time * satisficationWeights;

			}

			// ----------------------------------------
			cs.get(i).setSkillScore(score);

		}
	}
}