package Exchange.model.Account;

import java.sql.Date;

public class Profile 
{
	private String userName;
	private String nickName;
	private boolean gender;
	private boolean male;
	private boolean female;
	private String Email;
	private Date birthday;
	private String region;
	private int skillMax;
	private int skillNumber;
	
	public Profile(String userName, String nickName, boolean gender, String Email, Date birthday, String region, int skillMax, int skillNumber)
	{
		this.userName = userName;
		this.nickName = nickName;
		this.Email = Email;
		this.birthday = birthday;
		this.region = region;
		this.skillMax = skillMax;
		this.skillNumber = skillNumber;
		
		
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
		return Email;
	}
	
	public Date getbirthday()
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
		this.Email = Email;
	}
	
	public void setbirthday(Date birthday)
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
		return String.format("[userName: %s, nickName: %s, gender: %b, Email: %s, birthday: %tF, region: %s, skillMax: %d, skillNumber: %d]",
				getuserName(), getnickName(), getgender(), getEmail(), getbirthday(), getregion(), getskillMax(), getskillNumber());
	}
	
	
}
