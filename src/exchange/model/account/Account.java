package exchange.model.account;

import java.util.Date;

public class Account 
{
	private Secret secret;
	private Profile profile;
	private String reccntLog;
	
	public Account(Secret secret, Profile profile, String recentLog)
	{
		this.secret = secret;
		this.profile = profile;
		this.reccntLog = recentLog;
	}
	
	public Account(Profile profile) {
		// TODO Auto-generated constructor stub
		this.profile = profile;
	}

	public Account(Profile profile, Secret secret)
	{
		this.profile = profile;
		this.secret = secret;
	}
	
	public void setSecret(Secret secret)
	{
		this.secret = secret;
	}
	
	public Secret getSecret()
	{
		return secret;
	}
	
	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}
	
	public Profile getProfile()
	{
		return profile;
	}
	
	public void setRecentLog(String recentLog)
	{
		this.reccntLog = recentLog;
	}
	
	public String getRecentLog()
	{
		return reccntLog;
	}
	
	public String toString()
	{
		return String.format("Account[Secret = %s, Profile = %s, recentLog = %s ]", getSecret(), getProfile(), 
				getRecentLog());
	}
}
