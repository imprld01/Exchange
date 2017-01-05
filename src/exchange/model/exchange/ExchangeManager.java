package exchange.model.exchange;
import java.sql.ResultSet;
import java.util.ArrayList;
import exchange.model.database.DataBaseAdmin;
import exchange.model.exchange.Exchange;

public class ExchangeManager {
	public void acceptInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("INSERT INTO exchanges VALUES('"+othersSkillID+"','"+mySkillID+"','0')");
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+othersSkillID+"',ivt_reciever = '"+mySkillID+"'");
	}
	public void rejectInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+othersSkillID+"',ivt_reciever = '"+mySkillID+"'");
	}
	public void finishExchange(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("UPDATE exchanges SET end_flag = '1' where (skill_a = '"+othersSkillID+"' AND skill_b = '"+mySkillID+"')"
			+ "OR (skill_a = '"+mySkillID+"' AND skill_b = '"+othersSkillID+"')");
	}
	public void sendInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("INSERT INTO invitations VALUES('"+mySkillID+"','"+othersSkillID+")");
	}
	public ArrayList<Exchange> getExchangings(String mySkillID){
		ArrayList<Exchange> exchangeList = new ArrayList<Exchange>();
		ResultSet result = null;
		result = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges WHERE end_flag = '0' AND "
				+ "(skill_a = '"+mySkillID+"' OR skill_b = '"+mySkillID+"')");
		return exchangeList;
	}
	//a
}
