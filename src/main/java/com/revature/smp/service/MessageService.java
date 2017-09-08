package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.MessageClob;

public interface MessageService {
	List<MessageClob> getMostRecent(int messageRoomId);
	
	List<MessageClob> getPrevious(int messageRoomId, int messageClobId);
	
	List<MessageClob> getUpdate(int messageRoomId, int messageClobId);
	
	void postMessage(int messageRoomId, String user, String text);
	
	void cacheMessages(int messageRoomId, String user, String text);
}
