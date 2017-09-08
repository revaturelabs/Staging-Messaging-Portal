package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.MessageClob;

public interface MessageBlobService {
	List<MessageClob> getMostRecent(int messageRoomId);
	List<MessageClob> getPrevious(int messageRoomId, int messageBlobId);
	List<MessageClob> getUpdate(int messageRoomId, int messageBlobId);
	
	void postMessage(int messageRoomId, String user, String message);
}
