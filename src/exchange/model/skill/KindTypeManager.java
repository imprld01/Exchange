package exchange.model.skill;

import exchange.model.database.DataBaseAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KindTypeManager {

	static ArrayList<Kind> kindList = new ArrayList<Kind>();
	static ArrayList<Type> typeList = new ArrayList<Type>();

	//建構子
	public KindTypeManager(){
		//kindList = new ArrayList<Kind>();
		//typeList = new ArrayList<Type>();
		//updateKindList();
		//updateTypeList();
	}

	//取得資料哭中所有Kind
	static public ArrayList<Kind> getKindList(){
			
		String query = "SELECT * FROM classes";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		
		try {
			while (rs.next()) {
				kindList.add(new Kind(new Code(rs.getString("class_code")), rs.getString("class_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kindList;
	}

	//取得資料庫中所有Type
	static public ArrayList<Type> getTypeList(){
		
		String query = "SELECT * FROM types";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		
		try {
			while (rs.next()) {
				typeList.add(new Type(new Code(rs.getString("type_code")), 
									  rs.getString("type_name"),
									  new Code(rs.getString("class_code"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return typeList;
	}

	@Override
	public String toString() {
		return "KindTypeManager [kindList=" + kindList + ", typeList=" + typeList + "]";
	}
	
	public static void main(String[] args){
		
		for(Kind k:KindTypeManager.getKindList())
			System.out.println(k);
		
		for(Type t:KindTypeManager.getTypeList())
			System.out.println(t);
	}
}
