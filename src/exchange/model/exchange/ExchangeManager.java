package exchange.model.exchange;
import java.sql.ResultSet;
import java.util.ArrayList;
import exchange.model.database.DataBaseAdmin;
import exchange.model.exchange.Exchange;
import exchange.model.skill.SkillManager;;
public class ExchangeManager {
	//接受邀請, 新增到交流Table ,將邀請Table中原本的row刪除
	public static void acceptInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("INSERT INTO exchanges VALUES('"+othersSkillID+"','"+mySkillID+"','0')");
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+othersSkillID+"',ivt_reciever = '"+mySkillID+"'");
		//DataBaseAdmin.closeConnection();
	}
	//拒絕邀請, 刪除邀情Table中的邀請
	public static void rejectInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+othersSkillID+"',ivt_reciever = '"+mySkillID+"'");
		//DataBaseAdmin.closeConnection();
	}
	//完成交流, 將end_flag改成1
	public static void finishExchange(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("UPDATE exchanges SET end_flag = '1' where (skill_a = '"+othersSkillID+"' AND skill_b = '"+mySkillID+"')"
			+ "OR (skill_a = '"+mySkillID+"' AND skill_b = '"+othersSkillID+"')");
		//DataBaseAdmin.closeConnection();
	}
	//寄出邀請, 新增到邀請Table
	public static void sendInvitation(String mySkillID, String othersSkillID){
		//System.out.println("[mySkillID]:"+mySkillID+",[othersSkillID]:"+othersSkillID);
		DataBaseAdmin.updateDB("INSERT INTO invitations VALUES('"+mySkillID+"','"+othersSkillID+"')");
		//DataBaseAdmin.closeConnection();
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
				Exchange exchange = new Exchange(SkillManager.findSkill(exchangeResultA.getInt("skill_b")), exchangeResultA.getString("type_name"));
				//System.out.println("A"+exchangeResultA.getInt("skill_b")+exchangeResultA.getString("type_name"));
				exchangeList.add(exchange);
			}
			//處理我收到的邀請所進行的交流 並放進ArrayList
			while(exchangeResultB.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(exchangeResultB.getInt("skill_a")), exchangeResultB.getString("type_name"));
				//System.out.println("B"+exchangeResultB.getInt("skill_a")+exchangeResultB.getString("type_name"));
				exchangeList.add(exchange);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		//DataBaseAdmin.closeConnection();
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
				Exchange exchange = new Exchange(SkillManager.findSkill(receiveInvitationResult.getInt("ivt_sender")), receiveInvitationResult.getString("type_name"));
				//System.out.println(receiveInvitationResult.getInt("ivt_sender")+receiveInvitationResult.getString("type_name"));
				exchangeList.add(exchange);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		//DataBaseAdmin.closeConnection();
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
				Exchange exchange = new Exchange(SkillManager.findSkill(sendInvitationResult.getInt("ivt_receiver")), sendInvitationResult.getString("type_name"));
				//System.out.println(sendInvitationResult.getInt("ivt_receiver")+sendInvitationResult.getString("type_name"));
				exchangeList.add(exchange);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		//DataBaseAdmin.closeConnection();
		return exchangeList;
	}
	//拿到所有我的技能並且把所有與其交流的技能印出
	public static ArrayList<MySkill> getAllMySkills(String userID){
		ArrayList<MySkill> mySkills = new ArrayList<MySkill>();
		//由我寄出邀請而產生的交流
		ResultSet resultExchangeA = DataBaseAdmin.selectDB("SELECT skill_a,skill_b,type_name FROM exchanges  join skills on exchanges.skill_b = skills.skill_id where end_flag = '0' AND  "
				+"(skill_a in (select skill_id from skills where user_id = '"+userID+"'));");
		//由我收到邀請而產生的交流
				ResultSet resultExchangeB = DataBaseAdmin.selectDB("SELECT skill_a,skill_b,type_name FROM exchanges  join skills on exchanges.skill_a = skills.skill_id where end_flag = '0' AND  "
						+"(skill_b in (select skill_id from skills where user_id = '"+userID+"'));");
				ResultSet resultIdleSkill = DataBaseAdmin.selectDB("SELECT skill_id FROM skills where user_id = '"+userID+"'"
						+"AND (skill_id not in (select skill_a from exchanges where end_flag = '0')) "
						+"AND (skill_id not in (select skill_b from exchanges where end_flag = '0'))");
				try{
					//處理我送出的邀請所進行的交流 並放進ArrayList
					while(resultExchangeA.next()){
						MySkill mySkill = new MySkill(SkillManager.findSkill(resultExchangeA.getInt("skill_a")), resultExchangeA.getString("type_name"));
						//System.out.println("A"+resultExchangeA.getInt("skill_a")+resultExchangeA.getString("type_name"));
						mySkills.add(mySkill);
					}
					//處理我收到的邀請所進行的交流 並放進ArrayList
					while(resultExchangeB.next()){
						MySkill mySkill = new MySkill(SkillManager.findSkill(resultExchangeB.getInt("skill_b")), resultExchangeB.getString("type_name"));
						//System.out.println("A"+resultExchangeB.getInt("skill_b")+resultExchangeB.getString("type_name"));
						mySkills.add(mySkill);
					}
					//處理我閒置的技能並放進ArrayList
					while(resultIdleSkill.next()){
						MySkill mySkill = new MySkill(SkillManager.findSkill(resultIdleSkill.getInt("skill_id")), "");
						//System.out.println(resultIdleSkill.getInt("skill_id"));
						mySkills.add(mySkill);
					}
					
				}catch(Exception e){
					System.err.println(e.getMessage());
				}
				//DataBaseAdmin.closeConnection();
		return mySkills;
	}
	public static void main(String args[]){
		ExchangeManager.getAllMySkills("vegetable");
	}
}
