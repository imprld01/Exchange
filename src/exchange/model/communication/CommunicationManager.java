package exchange.model.communication;

import java.sql.ResultSet;
import java.util.ArrayList;

import exchange.model.database.DataBaseAdmin;

//新增訊息
public class CommunicationManager {
	public static boolean  newMsg(String sender, String receiver,String content){
		boolean isSuccess = true; //是否成功新增
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
	public static ArrayList<Message> receiveMessages(String getter, String other){
		ArrayList<Message>messages = new ArrayList<Message>();
		//從資料庫拿對方送出的訊息(未讀)
		ResultSet messagesSendByHe =DataBaseAdmin.selectDB("SELECT * FROM messages WHERE isRead='0'"
				+"msg_sender ='"+other+"' AND msg_receiver = '"+getter+"'");
		try{
			//負責處理由對方送出的未讀訊息 並存進Messages
			while(messagesSendByHe.next()){
				Message message = new Message(messagesSendByHe.getInt("msg_id"),messagesSendByHe.getString("msg_sender")
						,messagesSendByHe.getString("msg_receiver"),messagesSendByHe.getString("content")
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
	public static ArrayList<Message> getAllmessages(String getter, String other){
		ArrayList<Message>messages = new ArrayList<Message>();
		//負責從資料庫SELECT由我送出的訊息
		ResultSet messagesSendByMe =DataBaseAdmin.selectDB("SELECT * FROM messages WHERE"
				+"msg_sender ='"+getter+"' AND msg_receiver = '"+other+"'");
		//負責從資料庫拿取由別人送給我的訊息
		ResultSet messagesSendByHe =DataBaseAdmin.selectDB("SELECT * FROM messages WHERE"
				+"msg_sender ='"+other+"' AND msg_receiver = '"+getter+"'");
		try{
			//負責處理我由我送出的訊息 並存進Messages
			while(messagesSendByMe.next()){
				Message message = new Message(messagesSendByMe.getInt("msg_id"),messagesSendByMe.getString("msg_sender")
						,messagesSendByMe.getString("msg_receiver"),messagesSendByMe.getString("content")
						,messagesSendByMe.getBoolean("isRead"));
				messages.add(message);
				//DataBaseAdmin.updateDB("UPDATE messages SET isRead = '1' WHERE msg_id='"+message.getMsgID()+"'");
			}
			//負責處理由對方送出的訊息 並存進Messages
			while(messagesSendByHe.next()){
				Message message = new Message(messagesSendByHe.getInt("msg_id"),messagesSendByHe.getString("msg_sender")
						,messagesSendByHe.getString("msg_receiver"),messagesSendByHe.getString("content")
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
}
