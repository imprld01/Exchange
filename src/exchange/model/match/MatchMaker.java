package exchange.model.match;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MatchMaker {
    public abstract void match();
    public abstract void creatMateSet();
	class skillCard{ //依照表格排序地區  (魔術數字)
		int number;//編號
		String type_name;//技能名稱
		int skill_id;  //技能ID
		String region;  //居住地
		int distance;     //距離
		int attitude_score;//教學態度
		int profession_score;//技能程度
		int teaching_score;//教學技巧
		int frequency_score;//教學頻率
		int satisfication_score;//整體滿意度
	}
	
	
	class Area{ //依照表格排序地區  (魔術數字)
		int distance;
		String placeName;
		Area(int distance, String placeName) { this.distance=distance; this.placeName=placeName; }
	}
	Area[][] area = new Area[][]{{new Area(0,"基隆"),new Area(1,"台北"),new Area(2,"桃園"),new Area(2,"宜蘭"),new Area(3,"新竹"),new Area(4,"苗栗"),new Area(4,"花蓮"),new Area(5,"台中"),new Area(6,"彰化"),new Area(6,"南投"),new Area(7,"雲林"),new Area(7,"台東"),new Area(8,"嘉義"),new Area(9,"台南"),new Area(10,"高雄"),new Area(11,"屏東")}, //基隆
									
	
								};
	
	
    static Connection connection;

	public MatchMaker() {
		connection = null;
	}

	
	
	
	public static void openConnection() {
		System.out.println("-------- MySQL JDBC Connection ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found !!");
			return;
		}
		System.out.println("MySQL JDBC Driver Registered!");

		try {
			connection = DriverManager // jdbc:mysql://localhost:3306/data
					.getConnection("jdbc:mysql://localhost:3306/exchange?useUnicode=true&characterEncoding=UTF-8",
							"root", "a2n5h011oj");
			System.out.println("SQL Connection to database established!");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
			return;
		}

	}

	static public void get() throws SQLException {

		// SQL
		String sql = "select Count(*) as num,skills.*,accounts.region from skills,accounts,favorites where skills.account=accounts.user_id and favorites.account=accounts.user_id and favorites.type_name='吉他' and accounts.region='台北'  and skills.bad_tag=false and accounts.user_id!='john1105'  and accounts.recent_log-CURRENT_DATE()<3 order by rand(now()) limit 50;";

		// 不知道是什麼
		PreparedStatement statement = connection.prepareStatement(sql);
		// 執行SQL存入結果
		ResultSet result = statement.executeQuery();

		// 印出來
		System.out.println("num\t type_name\t region\t attitude_score\t profession_score\t teaching_score\t frequency_score\t satisfication_score\t ");
		
		while (result.next()) {

			System.out.println(result.getString("num") + "\t" + result.getString("type_name")+ "\t"+result.getString("region")+ "\t"+result.getString("attitude_score")+ "\t"+result.getString("profession_score")+ "\t"+result.getString("teaching_score")+ "\t"+result.getString("frequency_score")+ "\t"+result.getString("satisfication_score")+ "\t");
		}
	}

	public static void closeConnection() {

		try {
			if (connection != null)
				connection.close();
			System.out.println("Connection closed !!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
