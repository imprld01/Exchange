package exchange.model.account;

public class Profile 
{
	private String userName;
	private String nickName;
	private boolean gender;
	private boolean male;
	private boolean female;
	private String email;
	private String birthday;
	private String region;
	private int skillMax;
	private int skillNumber;
	
	public Profile(String userName, String nickName, boolean gender, String email, String birthday, String region, int skillMax, int skillNumber)
	{
		this.userName = userName;
		this.nickName = nickName;
		this.email = email;
		this.birthday = birthday;
		this.region = region;
		this.skillMax = 3;
		this.skillNumber = 0;
		
		
		boolean m = male;
		boolean f = female;
		if(gender == m)
		{
			System.out.println("male");
		}
		else if(gender == f)
		{
			System.out.println("female");
		}
	}
	
	public Profile(String nickName, String email, String region)
	{
		this.nickName = nickName;
		this.email = email;
		this.region = region;
	}
	
	public Profile(String userName, String nickName, boolean gender, String email, String birthday, String region)
	{
		this.userName = userName;
		this.nickName = nickName;
		this.email = email;
		this.birthday = birthday;
		this.region = region;
		this.getgender();
	}
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}

	public String getuserName()
	{
		return userName;
	}
	
	public String getnickName()
	{
		return nickName;
	}
	
	public boolean getgender()
	{
		return gender;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getbirthday()
	{
		return birthday;
	}
	
	public String getregion()
	{
		return region;
	}
	
	public int getskillMax()
	{
		return skillMax;
	}
	
	public int getskillNumber()
	{
		return skillNumber;
	}
	
	public void setnickName(String nickName)
	{
		this.nickName = nickName;
	}
	
	public void setuserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setgender(boolean gender)
	{
		this.gender = gender;
	}
	
	public void setEmail(String Email)
	{
		this.email = Email;
	}
	
	public void setbirthday(String birthday)
	{
		this.birthday = birthday;
	}
	
	public void setregion(String region)
	{
		this.region = region;
	}
	
	public void setskillMax(int skillMax)
	{
		this.skillMax = skillMax;
	}
	
	public void setskillNumber(int skillNumber)
	{
		this.skillNumber = skillNumber;
	}
	
	public String toString()
	{
		return String.format("Profile[userName: %s, nickName: %s, gender: %b, Email: %s, birthday: %s, region: %s, skillMax: %d, skillNumber: %d]",
				getuserName(), getnickName(), getgender(), getEmail(), getbirthday(), getregion(), getskillMax(), getskillNumber());
	}
}
