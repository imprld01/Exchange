package exchange.model.match;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.skill.*;
import exchange.model.account.*;
import exchange.model.database.DataBaseAdmin;



public  class BasicAlgorithm extends MatchMaker {
	public BasicAlgorithm(String user_id){
		connection=null;
		this.user_id=user_id;
	}
	
	static Connection connection;
	private ArrayList<Type> favoritesSkill;
	private String user_id;  //此帳號
	private String region;   //此帳號的地區
	private int regionNum=0; //此帳號的地區指標
	AccountManager accountManager=new AccountManager();
	
	
	
	
	class SkillCard{ 
		Skill skill;	
		int distance;     //距離
		double Score;
	}ArrayList<SkillCard> skillCard=new ArrayList<SkillCard>();

	class Area{ //依照表格排序地區  (魔術數字)
		int distance;
		String placeName;
		Area(int distance, String placeName) { this.distance=distance; this.placeName=placeName; }
	}
	Area[][] area = new Area[][]{{new Area(0,"基隆"),new Area(1,"台北"),new Area(2,"桃園"),new Area(2,"宜蘭"),new Area(3,"新竹"),new Area(4,"苗栗"),new Area(4,"花蓮"),new Area(5,"台中"),new Area(6,"彰化"),new Area(6,"南投"),new Area(7,"雲林"),new Area(7,"台東"),new Area(8,"嘉義"),new Area(9,"台南"),new Area(10,"高雄"),new Area(11,"屏東")}, //基隆
								 {new Area(0,"台北"),new Area(1,"基隆"),new Area(1,"桃園"),new Area(1,"宜蘭"),new Area(2,"新竹"),new Area(3,"苗栗"),new Area(3,"花蓮"),new Area(4,"台中"),new Area(5,"彰化"),new Area(5,"南投"),new Area(6,"雲林"),new Area(6,"台東"),new Area(7,"嘉義"),new Area(8,"台南"),new Area(9,"高雄"),new Area(10,"屏東")}, //台北	
	
								};

	
    public  void match(){};
    public  void creatMateSet(){
    	try {
			//setFavoriteSkill();
			favoritesSkill=SkillManager.getAllFavoriteSkills(user_id);
			region=accountManager.getRegion(user_id);	
			setRegionNum(region); //設定地區指標
			
			getMatchSkill();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    	DataBaseAdmin.closeConnection();
    	
    };
    
    
    //取得距離
    //取得技能名稱
    private void setRegionNum(String region){// 設定自己的地區陣列位置
    	switch (region) {//加入地區距離 先土法煉鋼
		case "基隆":
			regionNum=0;
			break;
		case "台北":
			regionNum=1;
			break;
		case "桃園":
			regionNum=2;
			break;
		case "新竹":
			regionNum=3;
			break;
		case "苗栗":
			regionNum=4;
			break;
		case "台中":
			regionNum=5;
			break;
		case "彰化":
			regionNum=6;
			break;
		case "南投":
			regionNum=7;
			break;
		case "雲林":
			regionNum=8;
			break;
		case "嘉義":
			regionNum=9;
			break;
		case "台南":
			regionNum=10;
			break;
		case "高雄":
			regionNum=11;
			break;
		case "屏東":
			regionNum=12;
			break;
		case "宜蘭":
			regionNum=13;
			break;
		case "花蓮":
			regionNum=14;
			break;
		case "台東":
			regionNum=15;
			break;		
		}
    }
    
    private int getDistance(String region){//取得對方與自己地區的距離
    	
    	int distance=0;
    	for(int i=0;i<16;i++){
    		if(area[regionNum][i].placeName.equals(region)){
    			distance=area[regionNum][i].distance;
    			break;
    		}
    	}
    	return distance;
    }
	//String type_name;//技能名稱	
	//int distance;     //距離
    
	private void getMatchSkill() throws SQLException {

		int nowdistance=0;
		int nowRegionNum=0;
		int limit=50;
		do{
			// SQL
			//String sql = "select skills.*,accounts.region from skills,accounts where skills.account=accounts.user_id and skills.bad_tag=false and datediff(CURRENT_DATE(),accounts.recent_log) <3 and accounts.user_id!='"+account+"' ";
			String sql = "select skills.*,accounts.region from skills,accounts where skills.user_id=accounts.user_id and skill_id not in(select invitations.ivt_sender from invitations where invitations.ivt_sender=skill_id) and skills.bad_tag=false and datediff(CURRENT_DATE(),accounts.recent_log) <3 and accounts.user_id!='"+user_id+"' ";
		
			for(int i=0;i<favoritesSkill.size();i++){  //加入興趣技能
				
				if(favoritesSkill.size()>1){
					if(i==0){
						sql=sql+"and (skills.type_name='"+favoritesSkill.get(i).getTypeName()+"' "; //多個興趣開頭+小括號
					}
					else if(i==favoritesSkill.size()-1){
						sql=sql+"or skills.type_name='"+favoritesSkill.get(i).getTypeName()+"') ";//多個興趣結尾+小括號
					}
					else{
						sql=sql+"or skills.type_name='"+favoritesSkill.get(i).getTypeName()+"' ";
					}
				}
				else{
					sql=sql+"and skills.type_name='"+favoritesSkill.get(i).getTypeName()+"' ";
				}
			}
			
			//----------------------------------------------------------------------------------
			for(int j=nowRegionNum;j<16;j++){
				if(j==nowRegionNum){ //第一次
					sql=sql+"and ( ";
				}
				
				nowdistance=area[regionNum][j].distance;
				sql=sql+"accounts.region='"+area[regionNum][j].placeName+"' ";
				if(j!=15&&nowdistance==area[regionNum][j+1].distance){ //如果下一筆距離一樣且不是最後一個地區
						sql=sql+"or ";	
				}
				else{
					sql=sql+") ";
					nowRegionNum=j+1;
					break;
				}	
			}
			
			
			
					
			sql=sql+" order by rand(now()) limit "+limit+"; "; 
			
			
			System.out.println(sql);
		
			ResultSet result = DataBaseAdmin.selectDB(sql);
			// 印出來
			//System.out.println("type_name\t region\t attitude_score\t profession_score\t teaching_score\t frequency_score\t satisfication_score\t ");
			
			while (result.next()) { //顯示當前抓了幾筆資料  預計在這將資料存入陣列
				
				
				limit--; //計算還需抓幾筆資料
				//distance=area[regionNum][nowRegionNum-1].distance
				
				
				System.out.println(result.getString("type_name")+ "\t"+result.getString("region")+ "\t"+result.getString("attitude_score")+ "\t"+result.getString("profession_score")+ "\t"+result.getString("teaching_score")+ "\t"+result.getString("frequency_score")+ "\t"+Boolean.parseBoolean(result.getString("bad_tag"))+ "\t");
				
				SkillCard sc=new SkillCard();
				Skill skill= SkillManager.findSkill(result.getInt("skill_id"));
	
				sc.skill=skill;
				//sc.Score=8.5;
				sc.distance=0;
				skillCard.add(sc);
				
				//System.out.println(sc.skill.getAccount());
			}
			//System.out.println(limit); 測試用
			
		}while(limit>0&&nowRegionNum<=15); //抓到50筆技能或全部地區資料抓完後跳出
		
		for(int i=0;i<skillCard.size();i++){
			System.out.println(skillCard.get(i).skill);
		}
		
		
		
	}

    
}
