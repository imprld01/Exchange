package exchange.model.skill;

import java.sql.ResultSet;
import java.sql.SQLException;

import exchange.model.database.DataBaseAdmin;

public class FavoriteSkill {
	private Type type;
	
	public FavoriteSkill(Type type)
	{
		this.type = new Type(type);
	}
}
 