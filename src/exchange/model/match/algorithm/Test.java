package exchange.model.match.algorithm;

import java.sql.SQLException;

import exchange.model.account.AccountManager;
import exchange.model.database.DataBaseAdmin;
import exchange.model.skill.Skill;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		DataBaseAdmin.changeDBAccount("root", "a2n5h011oj");

		//////////////////////////
		BasicAlgorithm ba = new BasicAlgorithm("vegetable",1); // 傳遞帳號與技能ID過去
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		System.out.println(ba.toMatch());
		

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
