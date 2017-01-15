package exchange.model.match.algorithm;

import java.sql.SQLException;

import exchange.model.account.AccountManager;
import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Skill;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		DataBaseAdmin.changeDBAccount("root", "root");

		//////////////////////////
		BasicAlgorithm ba = new BasicAlgorithm("vegetable",1); // 傳遞帳號與技能ID過去
		Skill temp = ba.toMatch();
		int i = 0;
		
		while(i != 200){
			System.out.println("["+i%50+"]"+temp);
			temp = ba.toMatch();
			if(temp == null){
				ba = new BasicAlgorithm("vegetable",1);
				temp = ba.toMatch();
			}
			i++;
		}
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
//		System.out.println(ba.toMatch());
		

		//Skill skill = new Skill();
		//System.out.println("-------------------------");

		/*AccountManager am = new AccountManager();
		do {
			skill = new Skill(basicAlgorithm.match());// 回傳null代表沒資料了
			System.out.println("[地區->" + am.getRegion(skill.getUserId()) + "]" + skill);
		} while (skill != null);
*/
	}

}
