package exchange.model.skill;

public class Type {
	private Code typeCode;
	private String typeName;

	public Type(Code typeCode, String typeName) {
		this.typeCode = new Code(typeCode);
		setTypeName(typeName);
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

	@Override
	public String toString() {
		return String.format("[ typeCode = %s, typeName = %s ]", getTypeCode(), getTypeName());
	}
}
