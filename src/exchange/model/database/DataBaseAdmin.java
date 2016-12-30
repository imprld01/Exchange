package exchange.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class DataBaseAdmin {
	
	private static  Connection connection = null;


	//建立連線
	public static void openConnection(String user,String pwd) {
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
					.getConnection("jdbc:mysql://localhost:3306/exchange?useUnicode=true&characterEncoding=UTF-8",user,pwd);
			System.out.println("SQL Connection to database established!");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			System.out.println(e.getMessage());
			return;
		}

	}
	//終止連線
	public static void closeConnection() {

		try {
			if (connection != null)
				connection.close();
			System.out.println("Connection closed !!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static ResultSet selectDB(String query){
		
		ResultSet result = null;
		try{
		PreparedStatement statement = connection.prepareStatement(query);
		result = statement.executeQuery();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return result;
	} 
	public  static void updateDB(String query){
		
		try{
		PreparedStatement statement = connection.prepareStatement(query);
		statement.executeUpdate(query);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return ;
	} 

	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet result = null;
		DataBaseAdmin.openConnection("root","root");
		String query = "UPDATE favorites SET type_name = 'swim' where account = 'admin'";
		DataBaseAdmin.updateDB(query);
		
	}

}