package exchange.model.skill;

public class Score {
	private int attitude;
	private int profession;
	private int teaching;
	private int frequency;
	private int satisfication;

	// 建構子()
	public Score() {
		this.attitude = 0;
		this.profession = 0;
		this.teaching = 0;
		this.frequency = 0;
		this.satisfication = 0;
	}

	// 建構子(int,int,int,int,int)
	public Score(int attitude, int profession, int teaching, int frequency, int satisfication) {
		this.attitude = attitude;
		this.profession = profession;
		this.teaching = teaching;
		this.frequency = frequency;
		this.satisfication = satisfication;
	}

	// 建構子(Score)
	public Score(Score score) {
		this(score.getAttitude(), score.getProfession(), score.getTeaching(), score.getFrequency(),
				score.getSatisfication());
	}

	public int getAttitude() {
		return attitude;
	}

	public int getProfession() {
		return profession;
	}

	public int getTeaching() {
		return teaching;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getSatisfication() {
		return satisfication;
	}

	//回傳所有分數相加
	public int calSumScore() {
		return (attitude + profession + teaching + frequency + satisfication);
	}

	@Override
	public String toString() {
		return "Score [attitude=" + attitude + ", profession=" + profession + ", teaching=" + teaching + ", frequency="
				+ frequency + ", satisfication=" + satisfication + "]";
	}
}
