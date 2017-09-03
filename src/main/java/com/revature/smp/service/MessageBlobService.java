package com.revature.smp.service;

import com.revature.smp.beans.MessageBlob;

public interface MessageBlobService {
	MessageBlob getMostRecentBlob(int messageRoomId);
	MessageBlob getPreviousBlob(int messageRoomId, int messageBlobId);
}
