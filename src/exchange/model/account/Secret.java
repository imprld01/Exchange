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
<<<<<<< HEAD
	
=======

>>>>>>> 829432907efc5faf4265795571291882933d0f9d
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
<<<<<<< HEAD
		return String.format("[id: %s, password: %s]", getId(), getPassword());
	}

	public boolean equalValue(Secret s)
	{
		if(!s.getId().equals(id))
			return false;
		else if(!s.equals(password))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object other) 
	{
		// TODO Auto-generated method stub
		if (other == null) 
			return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof Secret))
	    	return false;
	    if(equalValue((Secret) other)) 
	    	return true;
	    else return false;
=======
		return String.format("Secret[id = %s, password = %s]", getId(), getPassword());
>>>>>>> 829432907efc5faf4265795571291882933d0f9d
	}
}
