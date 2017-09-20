package com.revature.smp.services;

import java.util.List;

import com.revature.smp.beans.Message;

public interface MessageService {
	
	public boolean postMessage(Message message);
	
	List<Message> getMessagesByRoomId(int roomId);
	
	List<Message> getMessagesByRoomName(String roomName);
	
}
