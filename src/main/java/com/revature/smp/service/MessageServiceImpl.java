package com.revature.smp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.revature.smp.beans.Message;
import com.revature.smp.dao.MessageDAO;

import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDAO mdao;
	

	@Override
	public Message getMostRecentBlob(int messageRoomId) {
		List<Message> lm = mdao.findByMessageRoomIdOrderByMessageBlobIdDesc(messageRoomId);
		if(lm.size()>0) {
			return lm.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Message getPreviousBlob(int messageRoomId,int messageBlobId) {
		List<Message> lm = mdao.findByMessageRoomIdAndMessageBlobIdLessThanOrderByMessageBlobIdDesc(messageRoomId, messageBlobId);
		if(lm.size()>0) {
			return lm.get(0);
		}else {
			return null;
		}
	}

}
