package exchange.model.exchange;
import java.sql.ResultSet;
import java.util.ArrayList;
import exchange.model.database.DataBaseAdmin;
import exchange.model.exchange.Exchange;
import exchange.model.skill.Skill;
public class ExchangeManager {
	//接受邀請, 新增到交流Table ,將邀請Table中原本的row刪除
	public static void acceptInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("INSERT INTO exchanges VALUES('"+othersSkillID+"','"+mySkillID+"','0')");
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+othersSkillID+"',ivt_reciever = '"+mySkillID+"'");
		DataBaseAdmin.closeConnection();
	}
	//拒絕邀請, 刪除邀情Table中的邀請
	public static void rejectInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+othersSkillID+"',ivt_reciever = '"+mySkillID+"'");
		DataBaseAdmin.closeConnection();
	}
	//完成交流, 將end_flag改成1
	public static void finishExchange(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("UPDATE exchanges SET end_flag = '1' where (skill_a = '"+othersSkillID+"' AND skill_b = '"+mySkillID+"')"
			+ "OR (skill_a = '"+mySkillID+"' AND skill_b = '"+othersSkillID+"')");
		DataBaseAdmin.closeConnection();
	}
	//寄出邀請, 新增到邀請Table
	public static void sendInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("INSERT INTO invitations VALUES('"+mySkillID+"','"+othersSkillID+")");
		DataBaseAdmin.closeConnection();
	}
	//拿到使用者的所有相關交流, 並存成ArrayList<Exchange>回傳
	public static ArrayList<Exchange> getExchangings(String userID){
		ArrayList<Exchange> exchangeList = new ArrayList<Exchange>(); //回傳用
		//由我發出邀請的Exchange
		ResultSet exchangeResultA = DataBaseAdmin.selectDB("SELECT skill_b,skill_a,type_name FROM exchanges  join skills on exchanges.skill_a = skills.skill_id where end_flag = '0' AND"
				+"(skill_a in (select skill_id from skills where user_id = '"+userID+"'))");
		//別人邀請我的Exchange
		ResultSet exchangeResultB = DataBaseAdmin.selectDB("SELECT skill_a,skill_b,type_name FROM exchanges  join skills on exchanges.skill_b = skills.skill_id where end_flag = '0' AND"
				+"(skill_b in (select skill_id from skills where user_id = '"+userID+"'))");
		try{
			//處理我送出的邀請所進行的交流 並放進ArrayList
			while(exchangeResultA.next()){
				Exchange exchange = new Exchange(new Skill(exchangeResultA.getInt("skill_b")), exchangeResultA.getString("type_name"));
				//System.out.println("A"+exchangeResultA.getInt("skill_b")+exchangeResultA.getString("type_name"));
				exchangeList.add(exchange);
			}
			//處理我收到的邀請所進行的交流 並放進ArrayList
			while(exchangeResultB.next()){
				Exchange exchange = new Exchange(new Skill(exchangeResultB.getInt("skill_a")), exchangeResultB.getString("type_name"));
				//System.out.println("B"+exchangeResultB.getInt("skill_a")+exchangeResultB.getString("type_name"));
				exchangeList.add(exchange);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		DataBaseAdmin.closeConnection();
		return exchangeList;
	}
	//列出所有收到的邀請並回傳ArrayList
	public static ArrayList<Exchange> getReceiveInvitations(String userID){
		ArrayList<Exchange> exchangeList = new ArrayList<Exchange>(); //回傳用
		ResultSet receiveInvitationResult = DataBaseAdmin.selectDB("SELECT ivt_receiver,ivt_sender,type_name FROM invitations "
				+"join skills on invitations.ivt_receiver = skills.skill_id "
				+"where ivt_receiver in (select skill_id from skills where user_id = '"+userID+"')");
		try{
			//處理我收到的邀請 並放進ArrayList
			while(receiveInvitationResult.next()){
				Exchange exchange = new Exchange(new Skill(receiveInvitationResult.getInt("ivt_sender")), receiveInvitationResult.getString("type_name"));
				//System.out.println(receiveInvitationResult.getInt("ivt_sender")+receiveInvitationResult.getString("type_name"));
				exchangeList.add(exchange);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		DataBaseAdmin.closeConnection();
		return exchangeList;
	}
	//列出所有送出的邀請並回傳ArrayList
	public static ArrayList<Exchange> getSendInvitations(String userID){
		ArrayList<Exchange> exchangeList = new ArrayList<Exchange>(); //回傳用
		ResultSet sendInvitationResult = DataBaseAdmin.selectDB("SELECT ivt_receiver,ivt_sender,type_name FROM invitations "
				+"join skills on invitations.ivt_sender = skills.skill_id "
				+"where ivt_sender in (select skill_id from skills where user_id = '"+userID+"')");
		try{
			//處理我送出的邀請 並放進ArrayList
			while(sendInvitationResult.next()){
				Exchange exchange = new Exchange(new Skill(sendInvitationResult.getInt("ivt_receiver")), sendInvitationResult.getString("type_name"));
				System.out.println(sendInvitationResult.getInt("ivt_receiver")+sendInvitationResult.getString("type_name"));
				exchangeList.add(exchange);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		DataBaseAdmin.closeConnection();
		return exchangeList;
	}
	public static void main(String args[]){
		ExchangeManager.getSendInvitations("bowen");
	}
}
