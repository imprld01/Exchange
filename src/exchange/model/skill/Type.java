package exchange.model.skill;

public class Type {
	private Code typeCode;
	private String typeName;
	
	public Type(String code,String name)
	{
		typeCode = new Code(code);
		setTypeName(name);
	}

	public void setTypeName(String name) {
		typeName = name;
	}
	
	public Code getCode()
	{
		return typeCode;
	}
	
	public String getTypeName()
	{
		return typeName;
	}
	
	@Override
	public String toString()
	{
		return String.format("typeCode:%s\ttypeName:%s", getCode(), getTypeName());
	}
}
