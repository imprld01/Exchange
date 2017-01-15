package exchange.model.communication;

import exchange.model.database.DataBaseAdmin;

public class CommunicationManager {
	public static boolean  newMsg(String sender, String receiver,String content){
		boolean isSuccess = true;
		String query = " INSERT INTO messages  VALUES (0, '"+sender+"', '"+receiver+"', '"+content+"',0)";
		if(DataBaseAdmin.updateDB(query) == 0){isSuccess = false;};
		return isSuccess;
	}
}
