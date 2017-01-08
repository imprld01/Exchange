package exchange.model.skill;

import exchange.model.database.DataBaseAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KindTypeManager {

	ArrayList<Kind> kindList;
	ArrayList<Type> typeList;

	//建構子
	public KindTypeManager(){
		kindList = new ArrayList<Kind>();
		typeList = new ArrayList<Type>();

		updateKindList();
		updateTypeList();
	}

	//取得資料哭中所有Kind
	public void updateKindList(){

		String query = "SELECT * FROM classes";
		ResultSet rs = DataBaseAdmin.selectDB(query);
		
		try {
			while (rs.next()) {
				kindList.add(new Kind(new Code(rs.getString("class_code")), rs.getString("class_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//取得資料庫中所有Type
	public void updateTypeList(){

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
	}

	@Override
	public String toString() {
		return "KindTypeManager [kindList=" + kindList + ", typeList=" + typeList + "]";
	}
	
	public static void main(String[] args){
		
	}
}
