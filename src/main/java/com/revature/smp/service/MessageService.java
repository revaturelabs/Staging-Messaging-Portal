package com.revature.smp.service;

import com.revature.smp.beans.Message;

public interface MessageService {
	Message getMostRecentBlob(int messageRoomId);
	Message getPreviousBlob(int messageRoomId, int messageBlobId);
}
