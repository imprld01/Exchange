package exchange.model.match;

import java.sql.SQLException;

import exchange.model.database.DataBaseAdmin;

public class Test {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub


		// TODO Auto-generated method stub

		DataBaseAdmin.changeDBAccount("root","a2n5h011oj");
		
		//////////////////////////
		BasicAlgorithm basicAlgorithm =new BasicAlgorithm("vegetable",1); //傳遞帳號與技能ID過去
		basicAlgorithm.creatMateSet();
		
	
		
	}

}
