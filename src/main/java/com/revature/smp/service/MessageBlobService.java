package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.MessageBlob;

public interface MessageBlobService {
	List<MessageBlob> getMostRecent(int messageRoomId);
	List<MessageBlob> getPrevious(int messageRoomId, int messageBlobId);
	List<MessageBlob> getUpdate(int messageRoomId, int messageBlobId);
	
	void postMessage(int messageRoomId, String user, String message);
}
