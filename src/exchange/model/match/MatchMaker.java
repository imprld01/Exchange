package exchange.model.match;

import java.sql.SQLException;
import exchange.model.skill.Skill;

public abstract class MatchMaker {		
	    public abstract Skill match();
	    public abstract void creatMateSet();  
	    
    
}
