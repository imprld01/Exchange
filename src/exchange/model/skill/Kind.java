package exchange.model.skill;

public class Kind {
	private Code kindCode;
	private String kindName;

	public Kind(Code kindCode, String kindName) {
		this.kindCode = new Code(kindCode);
		setKindName(kindName);
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getKindName() {
		return kindName;
	}

	public Code getKindCode() {
		return kindCode;
	}

	public String toString() {
		return String.format("[ kindCode = %s, kindName = %s ]", getKindCode(), getKindName());

	}
}
