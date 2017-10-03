package com.revature.smp.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.smp.beans.Message;

public interface MessageService {
	
	boolean postMessage(Message message);
	
	List<Message> getMessagesByRoomId(int roomId);
	
	List<Message> getMessagesByRoomName(String roomName) throws SQLException;
	
}
