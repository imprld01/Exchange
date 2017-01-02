package exchange.model.skill;

public class Code {
	private String code;

	public Code(String code) {
		setCode(code);
	}
	
	public Code(Code code) {
		setCode(code.getCode());
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public boolean equalValue(Code ohertCode) {
		if ( !code.equals(ohertCode.getCode()))
			return false;
		return true;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Code))
			return false;

		if (equalValue((Code) other))
			return true;
		
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public String toString()
	{
		return String.format("[code = %s]",getCode());
	}
}
