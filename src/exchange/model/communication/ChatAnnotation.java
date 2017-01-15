/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package exchange.model.communication;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.EndpointConfig;
import org.json.JSONObject;

//import org.apache.juli.logging.Log;
//import org.apache.juli.logging.LogFactory;

import util.HTMLFilter;

@ServerEndpoint(value = "/websocket/chat/{sdr}")
public class ChatAnnotation {

	// private static final Log log = LogFactory.getLog(ChatAnnotation.class);

	private static final String GUEST_PREFIX = "Guest";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);
	public static Map<String, Session> clients = new HashMap<>();

	


	

	public static int getKey(String key){
		return Integer.parseInt(key);
	}
	@OnOpen
	public void start(@PathParam("sdr") int sender,Session session) {
		clients.put(Integer.toString(sender), session);
		//String message = String.format("* %s %s", nickname, "has joined.");
		ArrayList<Message> messages = CommunicationManager.getAllmessages(sender);
		broadcast(messages);
	}

	@OnClose
	public void end(@PathParam("sdr") int sender) {
		clients.remove(sender);
		//String message = String.format("* %s %s", "", "has disconnected.");
		//broadcast(message);
	}

	@OnMessage
	public void incoming(@PathParam("sdr") int sender,String message) {
		// Never trust the client
		CommunicationManager.newMsg(sender, message);
		ArrayList<Message> messages =  CommunicationManager.receiveMessages(CommunicationManager.getOthersID(sender));
		//String filteredMessage = String.format("%s: %s", nickname, HTMLFilter.filter(message.toString()));
		broadcast(messages);
	}

	@OnError
	public void onError(@PathParam("sdr") int sender,Throwable t) throws Throwable {
		// log.error("Chat Error: " + t.toString(), t);
		clients.remove(sender);
	}

	private static void broadcast(ArrayList<Message> messages) {
		//先把每則訊息包成json再做處理
		for(Message msg:messages){
			 Map jsonMap = new HashMap();
			 jsonMap.put("content", msg.getContent());
			 jsonMap.put("sdr", msg.getSender());
			 jsonMap.put("rcv", msg.getReceiver());
			 
			 JSONObject jsonMsg = new JSONObject(jsonMap);
			//掃過每個用戶來偵測是否符合接受的資格
			for (String client :  clients.keySet() ) {
				try {
					synchronized (client) {
						if(Integer.parseInt(client)==msg.getSender() || Integer.parseInt(client)==msg.getReceiver()){
							clients.get(client).getBasicRemote().sendText(jsonMsg.toString());
							if(msg.getReceiver() == Integer.parseInt(client))CommunicationManager.readMessage(msg.getMsgID());
						}
					}
				} catch (Exception e) {
					// log.debug("Chat Error: Failed to send message to client", e);
					clients.remove(client);
					try {
						clients.get(client).close();
					} catch (IOException e1) {
						// Ignore
					}
					//String message = String.format("* %s %s", client.nickname, "has been disconnected.");
					//broadcast(message);
				}
			}
		}
	}
}


