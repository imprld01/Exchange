package exchange.model.skill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {

	static Connection connection;

	public ConnectDB() {
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
		String sql = "SELECT * FROM `classes` ";

		// 不知道是什麼
		PreparedStatement statement = connection.prepareStatement(sql);
		// 執行SQL存入結果
		ResultSet result = statement.executeQuery();

		// 印出來
		System.out.println("class_code\tclass_name\t");
		
		while (result.next()) {

			System.out.println(result.getString("class_code") + "\t" + result.getString("class_name"));
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