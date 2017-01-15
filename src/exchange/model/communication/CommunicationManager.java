package exchange.model.communication;

import java.sql.ResultSet;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;
import exchange.model.exchange.ExchangeManager;

//新增訊息
public class CommunicationManager {
	//給ID拿到與其配對的ID
	public static int getOthersID(int id){
		int othersID = 0;
		ResultSet resultSdrID = DataBaseAdmin.selectDB("SELECT skill_a From exchanges where end_flag = '0' "
				+"AND skill_b = '"+id+"'");
		ResultSet resultRcvID = DataBaseAdmin.selectDB("SELECT skill_b From exchanges where end_flag = '0' "
				+"AND skill_a = '"+id+"'");
		try{
		while(resultSdrID.next()){othersID = resultSdrID.getInt("skill_a");}
		while(resultRcvID.next()){othersID = resultRcvID.getInt("skill_b");}

		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return othersID;
	}
	public static boolean  newMsg(int sender,String content){
		boolean isSuccess = true; //是否成功新增
		//拿到接收者的ID
		int receiver = getOthersID(sender);
		String query = " INSERT INTO messages  VALUES (0, '"+sender+"', '"+receiver+"', '"+content+"',0)";
		if(DataBaseAdmin.updateDB(query) == 0){isSuccess = false;};
		return isSuccess;
	}
	public static boolean readMessage(int msgID){
		boolean isSuccess = true; //是否成功修改
		String query = "UPDATE messages SET isRead = '1' WHERE msg_id='"+ msgID+"'";
		if(DataBaseAdmin.updateDB(query) == 0){isSuccess = false;};
		return isSuccess;
	}
	public static ArrayList<Message> receiveMessages(int getter){
		ArrayList<Message>messages = new ArrayList<Message>();
		//拿取對象的ID
		int other = getOthersID(getter);
		
		//從資料庫拿對方送出的訊息(未讀)
		ResultSet messagesSendByHe =DataBaseAdmin.selectDB("SELECT * FROM messages WHERE isRead='0' AND "
				+"msg_sender ='"+other+"' AND msg_receiver = '"+getter+"'");
		try{
			//負責處理由對方送出的未讀訊息 並存進Messages
			while(messagesSendByHe.next()){
				Message message = new Message(messagesSendByHe.getInt("msg_id"),messagesSendByHe.getInt("msg_sender")
						,messagesSendByHe.getInt("msg_receiver"),messagesSendByHe.getString("content")
						,messagesSendByHe.getBoolean("isRead"));
				messages.add(message);
				readMessage(message.getMsgID());
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		messages.sort(Message.getCompByMsgID()); //照ID由小到大排序
		return messages;
	}
	public static ArrayList<Message> getAllmessages(int getter){
		ArrayList<Message>messages = new ArrayList<Message>();
		//對象的ID
		int other =getOthersID(getter);
		
		//負責從資料庫SELECT由我送出的訊息
		ResultSet messagesSendByMe =DataBaseAdmin.selectDB("SELECT * FROM messages WHERE "
				+"msg_sender = '"+getter+"' AND msg_receiver = '"+other+"'");
		//負責從資料庫拿取由別人送給我的訊息
		ResultSet messagesSendByHe =DataBaseAdmin.selectDB("SELECT * FROM messages WHERE "
				+"msg_sender = '"+other+"' AND msg_receiver = '"+getter+"'");
		try{
			//負責處理我由我送出的訊息 並存進Messages
			while(messagesSendByMe.next()){
				Message message = new Message(messagesSendByMe.getInt("msg_id"),messagesSendByMe.getInt("msg_sender")
						,messagesSendByMe.getInt("msg_receiver"),messagesSendByMe.getString("content")
						,messagesSendByMe.getBoolean("isRead"));
				messages.add(message);
				//DataBaseAdmin.updateDB("UPDATE messages SET isRead = '1' WHERE msg_id='"+message.getMsgID()+"'");
			}
			//負責處理由對方送出的訊息 並存進Messages
			while(messagesSendByHe.next()){
				Message message = new Message(messagesSendByHe.getInt("msg_id"),messagesSendByHe.getInt("msg_sender")
						,messagesSendByHe.getInt("msg_receiver"),messagesSendByHe.getString("content")
						,messagesSendByHe.getBoolean("isRead"));
				messages.add(message);
				readMessage(message.getMsgID());
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		messages.sort(Message.getCompByMsgID()); //照ID由小到大排序
		return messages;
	}
	public static String getNickName(int id){
		String nickName = null;
		ResultSet resultNickName = DataBaseAdmin.selectDB("select nick_name from skills join accounts on skills.user_id = accounts.user_id"
				+" where skill_id = '"+id+"'");
		try{
			while(resultNickName.next()){nickName = resultNickName.getString("nick_name");}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return nickName;
	}
	public static void main(String args[]){
		/*CommunicationManager.newMsg(1,"GGGGGG");
		
		ArrayList<Message>messages1 = CommunicationManager.receiveMessages(2);
		for(Message msg:messages1 ){
			System.out.println(msg);
		}
		System.out.println("分隔線");
		ArrayList<Message> messages = CommunicationManager.getAllmessages(1);
		for(Message msg:messages ){
			System.out.println(msg);
		}*/
		System.out.println(CommunicationManager.getNickName(2));
	}
}
