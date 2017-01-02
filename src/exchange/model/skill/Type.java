package exchange.model.skill;

public class Type {
	private Code typeCode;
	private Code kindCode;
	private String typeName;
	
	
	public Type(Code typeCode, String typeName, Code kindCode) {
		this.typeCode = new Code(typeCode);
		this.kindCode = new Code(kindCode);
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

	public Code getKindCode() {
		return kindCode;
	}
	
	@Override
	public String toString() {
		return String.format("[typeCode = %s, typeName = %s, kindCode = %s]", getTypeCode(), getTypeName(), getKindCode());
	}
}
