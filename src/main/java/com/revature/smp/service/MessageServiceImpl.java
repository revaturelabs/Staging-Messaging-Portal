package com.revature.smp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.smp.beans.Message;
import com.revature.smp.dao.MessageDAO;

public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDAO mdao;
	

	@Override
	public Message getMostRecentBlob(int messageRoomId) {
		List<Message> lm = mdao.findByMessageRoomIdOrderByMessageBlobIdDesc(messageRoomId);
		return lm.get(0);
	}

	@Override
	public Message getPreviousBlob(int messageRoomId,int messageBlobId) {
		List<Message> lm = mdao.findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(messageRoomId, messageBlobId);
		return lm.get(0);
	}

}
