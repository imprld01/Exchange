package exchange.model.exchange;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import exchange.model.database.DataBaseAdmin;
import exchange.model.exchange.Exchange;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;;
public class ExchangeManager {
	//接受邀請, 新增到交流Table ,將邀請Table中原本的row刪除
	public static void acceptInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("INSERT INTO exchanges VALUES('"+othersSkillID+"','"+mySkillID+"','0')");
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+mySkillID+"'AND ivt_receiver = '"+othersSkillID+"'");
		//DataBaseAdmin.closeConnection();
	}
	//拒絕邀請, 刪除邀情Table中的邀請
	public static void rejectInvitation(String mySkillID, String othersSkillID){
		DataBaseAdmin.updateDB("DELETE FROM invitations Where ivt_sender = '"+mySkillID+"'AND ivt_receiver = '"+othersSkillID+"'");
		//DataBaseAdmin.closeConnection();
	}
	//完成交流, 將end_flag改成1
	//mySkillId是發送者:1
	//mySkillId是接收者:2
	//不會重複配對，所以不管資料表中會出現多筆結果
	public static boolean whichExchange(int mySkillID, int othersSkillID)
	{
		ResultSet resultA = DataBaseAdmin.selectDB("SELECT skill_b,skill_a FROM exchanges  where (end_a != '1' OR end_b != '1') AND"
				+" skill_a = '"+ mySkillID +"' AND skill_b ='"+othersSkillID+"'");
		
		try {
			if(resultA.next()){
				//送請求的人是我
				if(resultA.getInt("skill_a") == (mySkillID) ) return true;
			}else 
			{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//判斷是不是發送邀請的人
	public static void finishExchange(int mySkillID, int othersSkillID){
		
		//假設我是邀請者，然後我要結束交流
		if(whichExchange(mySkillID,othersSkillID)){
			DataBaseAdmin.updateDB("UPDATE exchanges SET end_a = '1' where skill_a = '"+mySkillID+"' AND skill_b = '"+othersSkillID+"'");
		}else{
			DataBaseAdmin.updateDB("UPDATE exchanges SET end_b = '1' where skill_a = '"+othersSkillID+"' AND skill_b = '"+mySkillID+"'");
		}
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
		ResultSet exchangeResultA = DataBaseAdmin.selectDB("SELECT skill_b,skill_a FROM exchanges  where (end_a = '0' AND end_b = '0') AND"
				+"(skill_a in (select skill_id from skills where user_id = '"+userID+"'))");
		//別人邀請我的Exchange
		ResultSet exchangeResultB = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges  where (end_a = '0' AND end_b = '0') AND"
				+"(skill_b in (select skill_id from skills where user_id = '"+userID+"'))");
		//由我寄出邀請而產生的交流，等待評論中
		ResultSet resultNotCommentA = DataBaseAdmin.selectDB("SELECT skill_b,skill_a FROM exchanges  where (end_a = '0' AND end_b = '1') AND"
				+"(skill_a in (select skill_id from skills where user_id = '"+userID+"'))");
		///別人邀請我而產生的交流，等待評論中
		ResultSet resultNotCommentB = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges  where (end_a = '1' AND end_b = '0') AND"
				+"(skill_b in (select skill_id from skills where user_id = '"+userID+"'))");
		try{
			//處理我送出的邀請所進行的交流 並放進ArrayList
			while(exchangeResultA.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(exchangeResultA.getInt("skill_a")), SkillManager.findSkill(exchangeResultA.getInt("skill_b")));
				//System.out.println("A"+exchangeResultA.getInt("skill_b")+exchangeResultA.getString("type_name"));
				exchangeList.add(exchange);
			}
			//處理我收到的邀請所進行的交流 並放進ArrayList
			while(exchangeResultB.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(exchangeResultB.getInt("skill_b")), SkillManager.findSkill(exchangeResultB.getInt("skill_a")));
				//System.out.println("B"+exchangeResultB.getInt("skill_a")+exchangeResultB.getString("type_name"));
				exchangeList.add(exchange);
			}
			//處理我送出的邀請所進行的交流 並放進ArrayList
			while(resultNotCommentA.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(resultNotCommentA.getInt("skill_a")), SkillManager.findSkill(resultNotCommentA.getInt("skill_b")),Exchange.UNFINISH_EXCHANGE);
				//System.out.println("A"+exchangeResultA.getInt("skill_b")+exchangeResultA.getString("type_name"));
				exchangeList.add(exchange);
			}
			//處理我收到的邀請所進行的交流 並放進ArrayList
			while(resultNotCommentB.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(resultNotCommentB.getInt("skill_b")), SkillManager.findSkill(resultNotCommentB.getInt("skill_a")),Exchange.UNFINISH_EXCHANGE);
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
		ResultSet receiveInvitationResult = DataBaseAdmin.selectDB("SELECT ivt_receiver,ivt_sender FROM invitations "
				+"where ivt_receiver in (select skill_id from skills where user_id = '"+userID+"')");
		try{
			//處理我收到的邀請 並放進ArrayList
			while(receiveInvitationResult.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(receiveInvitationResult.getInt("ivt_receiver")), SkillManager.findSkill(receiveInvitationResult.getInt("ivt_sender")));
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
		ResultSet sendInvitationResult = DataBaseAdmin.selectDB("SELECT ivt_receiver,ivt_sender FROM invitations "
				+"where ivt_sender in (select skill_id from skills where user_id = '"+userID+"')");
		try{
			//處理我送出的邀請 並放進ArrayList
			while(sendInvitationResult.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(sendInvitationResult.getInt("ivt_sender")), SkillManager.findSkill(sendInvitationResult.getInt("ivt_receiver")));
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
	public static ArrayList<Exchange> getAllMySkills(String userID){
		ArrayList<Exchange> mySkills = new ArrayList<Exchange>();
		//由我寄出邀請而產生的交流，且交流中
		ResultSet resultExchangeA = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges where end_a = '0' AND end_b = '0' AND  "
				+"(skill_a in (select skill_id from skills where user_id = '"+userID+"'));");
		//由我收到邀請而產生的交流，且交流中
		ResultSet resultExchangeB = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges where end_a = '0' AND end_b = '0' AND  "
				+"(skill_b in (select skill_id from skills where user_id = '"+userID+"'));");
		//由我寄出邀請而產生的交流，等待評論中
		ResultSet resultNotCommentA = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges where end_a = '0' AND end_b = '1' AND  "
				+"(skill_a in (select skill_id from skills where user_id = '"+userID+"'));");
		//由我收到邀請而產生的交流，等待評論中
		ResultSet resultNotCommentB = DataBaseAdmin.selectDB("SELECT skill_a,skill_b FROM exchanges where end_a = '1' AND end_b = '0' AND  "
				+"(skill_b in (select skill_id from skills where user_id = '"+userID+"'));");		
		//我的送出邀請的技能
		ResultSet resultSendingSkills = DataBaseAdmin.selectDB("SELECT ivt_sender,ivt_receiver FROM invitations where  "
				+"(ivt_sender in (select skill_id from skills where user_id = '"+userID+"'));");
		//我的收到邀請的技能
		ResultSet resultReceivingSkills = DataBaseAdmin.selectDB("SELECT DISTINCT  ivt_receiver FROM invitations where  "
				+"(ivt_receiver in (select skill_id from skills where user_id = '"+userID+"'));");
		//閒置中的技能
		ResultSet resultIdleSkill = DataBaseAdmin.selectDB("SELECT skill_id FROM skills where user_id = '"+userID+"'"
				+"AND (skill_id not in (select skill_a from exchanges where end_a = '0')) "
				+"AND (skill_id not in (select skill_b from exchanges where end_b = '0'))"
				+"AND (skill_id not in (select ivt_sender from invitations ))"
				+"AND (skill_id not in (select ivt_receiver from invitations ))");
		try{
			
			//處理我送出的邀請所進行的交流 並放進ArrayList
			while(resultExchangeA.next()){
				//System.out.println("resultExchangeA->YES");
				Exchange exchange = new Exchange(SkillManager.findSkill(resultExchangeA.getInt("skill_a")), SkillManager.findSkill(resultExchangeA.getInt("skill_b")));
				//System.out.println("[A]:"+resultExchangeA.getInt("skill_a")+","+resultExchangeA.getInt("skill_b"));
				//System.out.println(exchange);
				mySkills.add(exchange);
			}
			//處理我收到的邀請所進行的交流 並放進ArrayList
			while(resultExchangeB.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(resultExchangeB.getInt("skill_b")), SkillManager.findSkill(resultExchangeB.getInt("skill_a")));

				mySkills.add(exchange);
			}
			//處理我未進行評論的交流  並放進ArrayList
			while(resultNotCommentA.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(resultNotCommentA.getInt("skill_a")), SkillManager.findSkill(resultNotCommentA.getInt("skill_b")),Exchange.UNFINISH_EXCHANGE);
				//System.out.println("A"+resultExchangeA.getInt("skill_a")+resultExchangeA.getString("type_name"));
				mySkills.add(exchange);
			}
			//處理我未進行評論的交流  並放進ArrayList
			while(resultNotCommentB.next()){
				Exchange exchange = new Exchange(SkillManager.findSkill(resultNotCommentB.getInt("skill_b")), SkillManager.findSkill(resultNotCommentB.getInt("skill_a")),Exchange.UNFINISH_EXCHANGE);
				//System.out.println("A"+resultExchangeB.getInt("skill_b")+resultExchangeB.getString("type_name"));
				mySkills.add(exchange);
			}
			//處理我閒置的技能並放進ArrayList
			while(resultIdleSkill.next()){
				Exchange mySkill = new Exchange(SkillManager.findSkill(resultIdleSkill.getInt("skill_id")), Exchange.IDLE);
				//System.out.println(resultIdleSkill.getInt("skill_id"));
				mySkills.add(mySkill);
			}
			//處理我的送出邀請的技能
			while(resultSendingSkills.next()){
				Exchange mySkill = new Exchange(SkillManager.findSkill(resultSendingSkills.getInt("ivt_sender")), Exchange.SENDING_INVITATION);
				mySkills.add(mySkill);
			}
			//處理我的收到邀請的技能
			while(resultReceivingSkills.next()){
				Exchange mySkill = new Exchange(SkillManager.findSkill(resultReceivingSkills.getInt("ivt_receiver")), Exchange.RECEIVING_INVITATION);
				mySkills.add(mySkill);
			}			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		//DataBaseAdmin.closeConnection();
		return mySkills;
	}
	public static void main(String args[]){
		for(Exchange e:ExchangeManager.getAllMySkills("vegetable"))
			System.out.println("["+e.getStatus()+"]:"+e.getMySkill());
		
		System.out.println("getExchangings");
		for(Exchange e:ExchangeManager.getExchangings("vegetable"))
			System.out.println("["+e.getStatus()+"]:"+e.getMySkill());
		
		//System.out.println(ExchangeManager.whichExchange(1, 34));
	}
}
