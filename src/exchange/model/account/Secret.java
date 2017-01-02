package exchange.model.account;

public class Secret 
{
	private String id;
	private String password;
	
	public Secret(String id, String password)
	{
		this.id = id;
		this.password = password;
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
		return String.format("Secret[id = %s, password = %s]", getId(), getPassword());
	}
}
