package exchange.model.communication;

import java.util.Comparator;

public class Message {
	int sender;
	int receiver;
	boolean isRead;
	String content;
	int MsgID;
	public Message(int MsgID,int sender,int receiver,String content,boolean isRead) {
		this.sender = sender;
		this.receiver = receiver;
		this.isRead = isRead;
		this.MsgID = MsgID;
		this.content =content;
	}
	public static Comparator<Message> getCompByMsgID()
	{   
	 Comparator comp = new Comparator<Message>(){
	     @Override
	     public int compare(Message msg1, Message msg2)
	     {
	    	 if(msg1.getMsgID() > msg2.getMsgID())return 1;
	    	 else return -1;
	     }        
	 };
	 return comp;
	}  
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return  CommunicationManager.getNickName(sender)+": "+content;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMsgID() {
		return MsgID;
	}
	public void setMsgID(int msgID) {
		MsgID = msgID;
	}

}
