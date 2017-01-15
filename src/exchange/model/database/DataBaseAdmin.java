package exchange.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseAdmin {
	private static String userID = "root";
	private static String pwd = "root";
	private static Connection connection = null; // 用以建立連線之物件

	// 更改DB帳號密碼
	public static void changeDBAccount(String userID, String pwd) {
		DataBaseAdmin.userID = userID;
		DataBaseAdmin.pwd = pwd;
	}

	// 建立連線
	// 參數為資料庫帳號密碼
	public static void openConnection(String user, String pwd) {

		if (connection == null) {
			System.out.println("-------- MySQL JDBC Connection ------------");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("MySQL JDBC Driver not found !!");
				return;
			}
			System.out.println("MySQL JDBC Driver Registered!"); // 成功註冊JDBC
																	// DRIVER

			try {
				connection = DriverManager // jdbc:mysql://localhost:3306/data
						.getConnection("jdbc:mysql://localhost:3306/exchange?useUnicode=true&characterEncoding=UTF-8",
								user, pwd);
				// 與資料庫建立連線
				System.out.println("SQL Connection to database established!");

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console"); // 連線失敗
				System.out.println(e.getMessage());
				return;
			}
		}
		return;
	}

	// 終止連線
	// 使用完DB後需要使用者自行關閉
	public static void closeConnection() {

		try {
			if (connection != null)
				connection.close();
				connection = null;
			System.out.println("Connection closed !!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 用以下SELECT QUERY之method
	// String query = "select * from table_name";
	public static ResultSet selectDB(String query) {
		DataBaseAdmin.openConnection(DataBaseAdmin.userID, DataBaseAdmin.pwd); // 登入,帳號密碼
		ResultSet result = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			result = statement.executeQuery(); // 將SELECT結果存放於result物件
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// closeConnection();
		// 待result物件使用完畢之後才關閉DB
		return result;
	}

	// 用以下insert, update, delete QUERY之method
	// UPDATE favorites SET type_name = 'swim' where account = 'admin'
	// INSERT INTO Customers " + "VALUES (1001, 'Simpson', 'Mr.', 'Springfield',2001)
	// DELETE FROM Registration " +"WHERE id = 101";
	/**
	 * @param query
	 * @return
	 */
	public static int updateDB(String query) {
		int change = 0;
		DataBaseAdmin.openConnection(DataBaseAdmin.userID, DataBaseAdmin.pwd); // 登入,帳號密碼
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			change = statement.executeUpdate(query);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		// closeConnection(); //待使用結束由使用者自行關閉
		return change;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		// method 使用之範例參考
		ResultSet result = null; // 用以存放SELECT結果
		DataBaseAdmin.changeDBAccount("root", "root");// 更改DB帳號密碼
		// SELECT 範例
//		result = DataBaseAdmin.selectDB("SELECT * FROM accounts");
//		while (result.next()) {
//			System.out.println(result.getString("user_id") + " " + result.getString("user_name"));
//		}
		// 修改範例
		String query = "UPDATE favorites SET type_name = '吉他' where user_id = '0'"; // QUERY
		System.out.println(DataBaseAdmin.updateDB(query));

	}

	
}