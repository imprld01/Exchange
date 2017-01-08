package exchange.model.skill;

public class Code {
	private String code;

	// 建構子(String)
	public Code(String code) {
		setCode(code);
	}

	// 建構子(Code)
	public Code(Code code) {
		this(code.getCode());
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public boolean equalValue(Code ohertCode) {
		if (!code.equals(ohertCode.getCode()))
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
	public String toString() {
		return "Code [code=" + code + "]";
	}
	
	public static void main(String[] args){
	
	}
}
