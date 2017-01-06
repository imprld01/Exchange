package exchange.model.skill;

import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.account.AccountManager;
import exchange.model.database.DataBaseAdmin;

import exchange.model.database.DataBaseAdmin;


public class Test {

	public static void main(String[] args){
		// TODO Auto-generated method stub
//		Code code = new Code("123");
//		Code otherCode = new Code("1233");
//		
//		if( code.equals(otherCode)) System.out.println("yes");
//		else System.out.println("no");
//
//		Type type = new Type(new Code("MUS_001"),"音樂",new Code("MUS"));
//		System.out.println(type);
//
//		
//		Kind kind = new Kind(new Code("MUS"),"吉他");
//		System.out.println(kind);
//		
//		KindTypeManager kindType = new KindTypeManager();
//		System.out.println(kindType);
//		
//		Type type = new Type(new Code("MUS_001"),"音樂",new Code("MUS"));
//		System.out.println(type);
//
//		
//		Kind kind = new Kind(new Code("MUS"),"吉他");
//		System.out.println(kind);
//		
//		KindTypeManager kindType = new KindTypeManager();
//		System.out.println(KindTypeManager.findType("吉他"));
//		

//		Skill skill = new Skill(2);
//		System.out.println(skill);
//
		SkillManager sm = new SkillManager();
//		System.out.println(sm.getAllFavoriteSkills("vegetable"));

		//System.out.println(sm.getAllSkills("vegetable"));
		//System.out.println(AccountManager.isSkillFull("bowen"));
		//SkillManager.createFavoriteSkill("游泳", "bowen");
		//SkillManager.deleteFavoriteSkill("游泳", "bowen");
		//SkillManager.createSkill(new Skill("bowen","123123","吉他", new ArrayList<String>(), new ArrayList<String>()));
		
		SkillManager.updateSkillLevel(1);
	}

}
