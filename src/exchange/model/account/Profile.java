package exchange.model.account;

import exchange.model.match.Region;

public class Profile 
{
	private String userName;
	private String nickName;
	private boolean gender;
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
		this.skillMax = skillMax;
		this.skillNumber = skillNumber;
		this.gender = gender;
		
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
		this.gender = gender;
	}
	
	public Profile() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName()
	{
		return userName;
	}
	
	public String getNickName()
	{
		return nickName;
	}
	
	public boolean getGender()
	{
		boolean male = true;
		boolean female = false;
		if(gender == male)
			gender = true;
		if(gender == female)
			gender = false;
		return gender;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getBirthday()
	{
		return birthday;
	}
	
	public String getRegion()
	{
		return region;
	}
	
	public int getSkillMax()
	{
		return skillMax;
	}
	
	public int getSkillNumber()
	{
		return skillNumber;
	}
	
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setGender(boolean gender)
	{
		this.gender = gender;
	}
	
	public void setEmail(String Email)
	{
		this.email = Email;
	}
	
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	
	public void setRegion(String region)
	{
		this.region = region;
	}
	
	public void setSkillMax(int skillMax)
	{
		this.skillMax = skillMax;
	}
	
	public void setSkillNumber(int skillNumber)
	{
		this.skillNumber = skillNumber;
	}
	
	public Region getRegionObj(){
		
		switch(this.region){
		case "基隆": return Region.KEELUNG;
		case "台北": return Region.TAIPEI;
		case "桃園": return Region.TAOYUAN;
		case "新竹": return Region.HSINCHU;
		case "苗栗": return Region.MIAOLI;
		case "台中": return Region.TAICHUNG;
		case "彰化": return Region.CHANGHUA;
		case "南投": return Region.NANTOU;
		case "雲林": return Region.YUNLIN;
		case "嘉義": return Region.CHIAYI;
		case "台南": return Region.TAINAN;
		case "高雄": return Region.KAOHSIUNG;
		case "屏東": return Region.PINGTUNG;
		case "宜蘭": return Region.ILAN;
		case "花蓮": return Region.HUALIEN;
		case "台東": return Region.TAITUNG;
		}
		
		return null;
	}
	
	public String toString()
	{
		return String.format("Profile[userName: %s, nickName: %s, gender: %b, Email: %s, birthday: %s, region: %s, skillMax: %d, skillNumber: %d]",
				getUserName(), getNickName(), getGender(), getEmail(), getBirthday(), getRegion(), getSkillMax(), getSkillNumber());
	}
}
