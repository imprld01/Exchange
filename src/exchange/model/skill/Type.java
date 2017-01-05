package exchange.model.skill;

public class Type {
	private Code typeCode;
	private Code kindCode;
	private String typeName;

	// 建構子()
	public Type() {
		this.typeCode = new Code("");
		this.kindCode = new Code("");
		setTypeName("");
	}

	// 建構子(Code,String,Code)
	public Type(Code typeCode, String typeName, Code kindCode) {
		this.typeCode = new Code(typeCode);
		this.kindCode = new Code(kindCode);
		setTypeName(typeName);
	}

	// 建構子(Type)
	public Type(Type type) {
		this(type.getKindCode(), type.getTypeName(), type.getTypeCode());
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public Code getTypeCode() {
		return typeCode;
	}

	public Code getKindCode() {
		return kindCode;
	}

	@Override
	public String toString() {
		return "Type [typeCode=" + typeCode + ", kindCode=" + kindCode + ", typeName=" + typeName + "]";
	}
}
