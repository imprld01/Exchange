package exchange.model.skill;

import java.sql.SQLException;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;

import exchange.model.database.DataBaseAdmin;


public class Test {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub
//		Code code = new Code("123");
//		Code otherCode = new Code("1233");
//		
//		if( code.equals(otherCode)) System.out.println("yes");
//		else System.out.println("no");
//
<<<<<<< HEAD
		
		Type type = new Type(new Code("MUS_001"),"音樂",new Code("MUS"));
		System.out.println(type);

		
		Kind kind = new Kind(new Code("MUS"),"吉他");
		System.out.println(kind);
		
		KindTypeManager kindType = new KindTypeManager();
		System.out.println(kindType);
		
		Skill skill = new Skill(1);
		System.out.println(skill);
		
=======
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

		Skill skill = new Skill(2);
		System.out.println(skill);

		SkillManager sm = new SkillManager();
		System.out.println(sm.getAllFavoriteSkills("vegetable"));
<<<<<<< HEAD
		System.out.println(sm.getAllSkills("vegetable"));
		sm.createSkill(new Skill("vegetable","123123",new Type("游泳"), new ArrayList<String>(), new ArrayList<String>()));
=======
>>>>>>> 0bd78bd6d64e13490cef64003c8cfeb31b6ed52f
>>>>>>> 303d28a1f1a7a0d0ba6cab0771bb6e6bb05141da
	}

}
