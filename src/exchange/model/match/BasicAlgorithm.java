package exchange.model.match;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import exchange.model.skill.*;
import exchange.model.account.*;
import exchange.model.database.DataBaseAdmin;



public  class BasicAlgorithm extends MatchMaker {
	private ArrayList<Type> favoritesSkill;
	private ArrayList<Skill> myAllSkill;
	private String user_id;  //此帳號
	private String region;   //此帳號的地區
	private Skill mySkill;   //使用哪個技能進行交換
	private int regionNum=0; //此帳號的地區指標
	private AccountManager accountManager=new AccountManager();
	private ArrayList<SkillCard> skillCard=new ArrayList<SkillCard>();
	private Area[][] area = new Area[][]{{new Area(0,"基隆"),new Area(1,"台北"),new Area(2,"桃園"),new Area(2,"宜蘭"),new Area(3,"新竹"),new Area(4,"苗栗"),new Area(4,"花蓮"),new Area(5,"台中"),new Area(6,"彰化"),new Area(6,"南投"),new Area(7,"雲林"),new Area(7,"台東"),new Area(8,"嘉義"),new Area(9,"台南"),new Area(10,"高雄"),new Area(11,"屏東")}, //基隆
										 {new Area(0,"台北"),new Area(1,"基隆"),new Area(1,"桃園"),new Area(1,"宜蘭"),new Area(2,"新竹"),new Area(3,"苗栗"),new Area(3,"花蓮"),new Area(4,"台中"),new Area(5,"彰化"),new Area(5,"南投"),new Area(6,"雲林"),new Area(6,"台東"),new Area(7,"嘉義"),new Area(8,"台南"),new Area(9,"高雄"),new Area(10,"屏東")},  //台北	
										 {new Area(0,"桃園"),new Area(1,"台北"),new Area(1,"新竹"),new Area(2,"基隆"),new Area(2,"宜蘭"),new Area(2,"苗栗"),new Area(3,"台中"),new Area(4,"彰化"),new Area(4,"南投"),new Area(4,"花蓮"),new Area(5,"雲林"),new Area(6,"嘉義"),new Area(7,"台南"),new Area(7,"台東"),new Area(8,"高雄"),new Area(9,"屏東")},   //桃園	
										 {new Area(0,"新竹"),new Area(1,"桃園"),new Area(1,"苗栗"),new Area(2,"台北"),new Area(2,"台中"),new Area(3,"基隆"),new Area(3,"彰化"),new Area(3,"南投"),new Area(3,"宜蘭"),new Area(4,"雲林"),new Area(5,"嘉義"),new Area(5,"花蓮"),new Area(6,"台南"),new Area(7,"高雄"),new Area(7,"台東"),new Area(8,"屏東")},   //新竹	
										 {new Area(0,"苗栗"),new Area(1,"新竹"),new Area(1,"台中"),new Area(2,"桃園"),new Area(2,"彰化"),new Area(2,"南投"),new Area(3,"台北"),new Area(3,"雲林"),new Area(4,"基隆"),new Area(4,"嘉義"),new Area(4,"宜蘭"),new Area(5,"台南"),new Area(6,"高雄"),new Area(6,"花蓮"),new Area(7,"屏東"),new Area(9,"台東")},   //苗栗	
										 {new Area(0,"台中"),new Area(1,"苗栗"),new Area(1,"彰化"),new Area(1,"南投"),new Area(2,"新竹"),new Area(2,"雲林"),new Area(3,"桃園"),new Area(3,"嘉義"),new Area(4,"台北"),new Area(4,"台南"),new Area(5,"基隆"),new Area(5,"高雄"),new Area(5,"宜蘭"),new Area(5,"花蓮"),new Area(6,"屏東"),new Area(8,"台東")},   //台中	
										 {new Area(0,"彰化"),new Area(1,"台中"),new Area(1,"南投"),new Area(1,"雲林"),new Area(2,"苗栗"),new Area(2,"嘉義"),new Area(3,"新竹"),new Area(3,"台南"),new Area(4,"桃園"),new Area(4,"高雄"),new Area(5,"台北"),new Area(5,"屏東"),new Area(5,"花蓮"),new Area(6,"基隆"),new Area(6,"宜蘭"),new Area(8,"台東")},   //彰化	
										 {new Area(0,"南投"),new Area(1,"台中"),new Area(1,"彰化"),new Area(2,"苗栗"),new Area(2,"雲林"),new Area(3,"新竹"),new Area(3,"嘉義"),new Area(4,"桃園"),new Area(5,"台南"),new Area(4,"花蓮"),new Area(5,"台北"),new Area(5,"高雄"),new Area(6,"基隆"),new Area(6,"屏東"),new Area(6,"宜蘭"),new Area(7,"台東")},   //南投	
										 {new Area(0,"雲林"),new Area(1,"彰化"),new Area(1,"嘉義"),new Area(2,"台中"),new Area(2,"南投"),new Area(2,"台南"),new Area(3,"苗栗"),new Area(3,"高雄"),new Area(4,"新竹"),new Area(4,"屏東"),new Area(5,"桃園"),new Area(6,"台北"),new Area(6,"花蓮"),new Area(7,"基隆"),new Area(7,"宜蘭"),new Area(7,"台東")},   //雲林	
										 {new Area(0,"嘉義"),new Area(1,"雲林"),new Area(1,"台南"),new Area(2,"彰化"),new Area(2,"高雄"),new Area(3,"台中"),new Area(3,"南投"),new Area(3,"屏東"),new Area(4,"苗栗"),new Area(5,"新竹"),new Area(6,"桃園"),new Area(6,"台東"),new Area(7,"台北"),new Area(7,"花蓮"),new Area(8,"基隆"),new Area(8,"宜蘭")},   //嘉義	
										 {new Area(0,"台南"),new Area(1,"嘉義"),new Area(1,"高雄"),new Area(2,"雲林"),new Area(2,"屏東"),new Area(3,"彰化"),new Area(4,"台中"),new Area(4,"南投"),new Area(5,"苗栗"),new Area(5,"台東"),new Area(6,"新竹"),new Area(7,"桃園"),new Area(8,"台北"),new Area(8,"花蓮"),new Area(9,"基隆"),new Area(9,"宜蘭")},   //台南	
										 {new Area(0,"高雄"),new Area(1,"台南"),new Area(1,"屏東"),new Area(2,"嘉義"),new Area(3,"雲林"),new Area(4,"彰化"),new Area(4,"台東"),new Area(5,"台中"),new Area(5,"南投"),new Area(6,"苗栗"),new Area(7,"新竹"),new Area(7,"花蓮"),new Area(8,"桃園"),new Area(9,"台北"),new Area(9,"宜蘭"),new Area(10,"基隆")},  //高雄	
										 {new Area(0,"屏東"),new Area(1,"高雄"),new Area(1,"台南"),new Area(3,"嘉義"),new Area(3,"台東"),new Area(4,"雲林"),new Area(5,"彰化"),new Area(6,"台中"),new Area(6,"南投"),new Area(6,"花蓮"),new Area(7,"苗栗"),new Area(8,"新竹"),new Area(8,"宜蘭"),new Area(9,"桃園"),new Area(10,"台北"),new Area(11,"基隆")}, //屏東	
										 {new Area(0,"宜蘭"),new Area(1,"台北"),new Area(2,"基隆"),new Area(2,"桃園"),new Area(2,"花蓮"),new Area(3,"新竹"),new Area(4,"苗栗"),new Area(5,"台中"),new Area(5,"台東"),new Area(6,"彰化"),new Area(6,"南投"),new Area(7,"雲林"),new Area(8,"嘉義"),new Area(8,"屏東"),new Area(9,"台南"),new Area(9,"高雄")},   //宜蘭	
										 {new Area(0,"花蓮"),new Area(2,"宜蘭"),new Area(3,"台北"),new Area(3,"台東"),new Area(4,"基隆"),new Area(4,"桃園"),new Area(4,"南投"),new Area(5,"桃園"),new Area(5,"台中"),new Area(5,"彰化"),new Area(6,"苗栗"),new Area(6,"雲林"),new Area(6,"屏東"),new Area(7,"嘉義"),new Area(7,"高雄"),new Area(8,"台南")},   //花蓮	
										 {new Area(0,"台東"),new Area(3,"屏東"),new Area(3,"花蓮"),new Area(4,"高雄"),new Area(5,"台南"),new Area(5,"宜蘭"),new Area(6,"台北"),new Area(6,"嘉義"),new Area(7,"基隆"),new Area(7,"桃園"),new Area(7,"南投"),new Area(7,"雲林"),new Area(8,"新竹"),new Area(8,"台中"),new Area(8,"彰化"),new Area(9,"苗栗")},   //花蓮								 
										};
	private int cardNumber=0;
	
	public BasicAlgorithm(String user_id,int skill_id){
		this.user_id=user_id;
		this.mySkill=SkillManager.findSkill(skill_id);
	}
	
    public Skill match(){
    	if(skillCard.size()==0||cardNumber>=skillCard.size()){ //沒有卡片，或是已經最後一張卡
    		return null;
    	}
    	else{
	    	Skill skill=skillCard.get(cardNumber).getSkill();
	    	cardNumber++;
	    	return skill;
    	}
    };
    
    public  void creatMateSet(){
    	try {
			
			favoritesSkill=SkillManager.getAllFavoriteSkills(user_id);
			myAllSkill=SkillManager.getAllSkills(user_id);
			region=accountManager.getRegion(user_id);	
			setRegionNum(region); //設定地區指標		
			getMatchSkill();	  //撈取資料
			computeDistanceCoefficient();//計算距離係數
			computeSkillScore(); //計算權重與分數
			sort(); //排序
			System.out.println(skillCard.get(1).getSkill().getType().getKindCode());
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	DataBaseAdmin.closeConnection();
    	
    };
    
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
    
	private void computeDistanceCoefficient(){//計算距離係數
		
				int distancedifference=0;	  //距離差
				int distanceCoefficient=0;    //距離係數
				int distanceCoefficientSum=0; //距離係數總和
				for(int i=0;i<skillCard.size();i++){  //計算距離係數		
					distanceCoefficient=skillCard.size()-distancedifference;
					distanceCoefficientSum+=distanceCoefficient; //累加距離係數
					skillCard.get(i).setDistanceCoefficient(distanceCoefficient);
					
					if(i!=skillCard.size()-1&&skillCard.get(i).getDisrance()!=skillCard.get(i+1).getDisrance()){ //如果下一個技能距離不同
						distancedifference++;
					}			
					
				}
						
				for(int i=0;i<skillCard.size();i++){ 
					skillCard.get(i).setDistanceCoefficientSum(distanceCoefficientSum);
				}	
	}

	private void computeSkillScore(){ //計算卡片分數
		for(int i=0;i<skillCard.size();i++){
			boolean hasSkill=false;
			//----------Weights--------------
			double attitudeWeights;
			double professionWeights;
			double teachingWeights;
			double frequencyWeights;
			double satisficationWeights;
			double score;
			//------------計算權重-------------------
			if(mySkill.getTimes()!=0){  //如果為新卡片
				attitudeWeights=mySkill.getScore().getAttitude()/mySkill.getTimes(); //教學態度權重
				satisficationWeights=mySkill.getScore().getSatisfication()/mySkill.getTimes();//整體滿意度權重
				if(mySkill.getSkillLevel()<=5){frequencyWeights=3;} //教學頻率權重
				else{frequencyWeights=1;}
				
			}
			else{ //如果為舊卡片
				attitudeWeights=3;
				frequencyWeights=3;
				satisficationWeights=3;
			}
			
			for(int j=0;j<myAllSkill.size();j++){ //判斷自己是否有該技能(程度與技巧權重)
				if(skillCard.get(i).getSkill().getType().getTypeName().equals(myAllSkill.get(j).getType().getTypeName())){
					hasSkill=true;
					break;
				}
			}
			
			if(hasSkill){  //有該技能
				professionWeights=4;
				teachingWeights=2;
			}
			else{  //沒有該技能
				professionWeights=2;
				teachingWeights=4;
			}
			//---------------------------------------
			//--------------計算分數-----------------
			int time=skillCard.get(i).getSkill().getTimes();   
			if(time==0){score=0;}
			else{
				score=((double)skillCard.get(i).getSkill().getScore().getAttitude()/time*attitudeWeights+	  
					   (double)skillCard.get(i).getSkill().getScore().getProfession()/time*professionWeights+	
					   (double)skillCard.get(i).getSkill().getScore().getTeaching()/time*teachingWeights+
					   (double)skillCard.get(i).getSkill().getScore().getFrequency()/time*frequencyWeights+
					   (double)skillCard.get(i).getSkill().getScore().getSatisfication()/time*satisficationWeights)*
					   skillCard.get(i).getDistanceCoefficient()/skillCard.get(i).getDistanceCoefficientSum();
						
			}
			
			//----------------------------------------
			skillCard.get(i).setScore(score);
			
		}
	}
	    
	private void sort(){ //依照分數排序
		Collections.sort(skillCard);
	} 
	
	private void getMatchSkill() throws SQLException {

		int nowdistance=0;
		int nowRegionNum=0;
		int limit=50;
		do{
			// SQL
			//String sql = "select skills.*,accounts.region from skills,accounts where skills.account=accounts.user_id and skills.bad_tag=false and datediff(CURRENT_DATE(),accounts.recent_log) <3 and accounts.user_id!='"+account+"' ";
			String sql = "select skills.skill_id from skills,accounts where skills.user_id=accounts.user_id and skill_id not in(select invitations.ivt_sender from invitations where invitations.ivt_sender=skill_id) and skills.bad_tag=false and datediff(CURRENT_DATE(),accounts.recent_log) <3 and skills.user_id!='"+user_id+"' ";
		
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
				
				nowdistance=area[regionNum][j].getDistance();
				sql=sql+"accounts.region='"+area[regionNum][j].getPlaceName()+"' ";
				if(j!=15&&nowdistance==area[regionNum][j+1].getDistance()){ //如果下一筆距離一樣且不是最後一個地區
						sql=sql+"or ";	
				}
				else{
					sql=sql+") ";
					nowRegionNum=j+1;
					break;
				}	
			}
			
							
			sql=sql+" order by rand(now()) limit "+limit+"; "; 
			
			
			//System.out.println(sql);
		
			ResultSet result = DataBaseAdmin.selectDB(sql);
			
			while (result.next()) { //顯示當前抓了幾筆資料  預計在這將資料存入陣列
				
				
				limit--; //計算還需抓幾筆資料
				
				//System.out.println(result.getString("skill_id"));
				
				
				Skill skill= SkillManager.findSkill(result.getInt("skill_id"));
				int distance=nowdistance;
				SkillCard sc=new SkillCard(skill,distance);
				skillCard.add(sc);
				
				
				
				//System.out.println(sc.skill.getAccount());
			}
			//System.out.println(limit); 測試用
			
		}while(limit>0&&nowRegionNum<=15); //抓到50筆技能或全部地區資料抓完後跳出
		
		
	
		
		//-------------------------------------------------------------------------------------
		
		
		//--------------------------------------------------------------------------
		
		
		
		
		
		
	}

    
}
