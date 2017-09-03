package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.MessageBlob;

public interface MessageBlobService {
	List<MessageBlob> getMostRecentBlob(int messageRoomId);
	List<MessageBlob> getPreviousBlob(int messageRoomId, int messageBlobId);
}
