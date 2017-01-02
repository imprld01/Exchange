package exchange.model.skill;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub
//		Code code = new Code("123");
//		Code otherCode = new Code("1233");
//		
//		if( code.equals(otherCode)) System.out.println("yes");
//		else System.out.println("no");
//
//		Type type = new Type(new Code("MUS"),"音樂");
//		System.out.println(type);
//		
//		Kind kind = new Kind(new Code("MUS_001"),"吉他");
//		System.out.println(kind);
		
		//KindTypeManager kind = new KindTypeManager();
		//System.out.println(kind);
		//////////////////////////

		ConnectDB.openConnection();
		ConnectDB.get();
	}

}
