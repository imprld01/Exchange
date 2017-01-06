package exchange.model.skill;

public class Kind {
	private Code kindCode;
	private String kindName;

	// 建構子(Code,String)
	public Kind(Code kindCode, String kindName) {
		this.kindCode = new Code(kindCode);
		setKindName(kindName);
	}

	// 建構子(Kind)
	public Kind(Kind kind) {
		this(kind.getKindCode(), kind.getKindName());
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

	@Override
	public String toString() {
		return "Kind [kindCode=" + kindCode + ", kindName=" + kindName + "]";
	}
}
