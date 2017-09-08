package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.MessageClob;

public interface MessageClobService {
	List<MessageClob> getMostRecent(int messageRoomId);
	
	List<MessageClob> getPrevious(int messageRoomId, int messageClobId);
	
	List<MessageClob> getUpdate(int messageRoomId, int messageClobId);
	
	void postMessage(int messageRoomId, String user, String message);
}
