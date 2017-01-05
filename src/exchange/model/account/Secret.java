package exchange.model.account;

public class Secret 
{
	private String id;
	private String password;
	
	public Secret(String id, String password)
	{
		this.id = new String();
		this.password = new String();
	}
	
	public Secret(String id) {
		//getAlluserid()
		this.id = id;
	}

	public Secret() {
		// TODO Auto-generated constructor stub
	}

	public String getId()
	{
		return id;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String toString()
	{
		return String.format("[id= %s, password= %s]", getId(), getPassword());
	}
}
