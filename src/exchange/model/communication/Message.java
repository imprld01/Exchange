package exchange.model.communication;

public class Message {
	String sender;
	String receiver;
	String isRead;
	int MsgID;
	public Message(int MsgID,String sender,String receiver,String isRead) {
		this.sender = sender;
		this.receiver = receiver;
		this.isRead = isRead;
		this.MsgID = MsgID;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public int getMsgID() {
		return MsgID;
	}
	public void setMsgID(int msgID) {
		MsgID = msgID;
	}

}
