package com.revature.smp.service;

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
		return mdao.getMostRecent(messageRoomId);
	}

	@Override
	public Message getPreviousBlob(int messageRoomId,int messageBlobId) {
		return mdao.getPrevious(messageRoomId, messageBlobId);
	}

}
