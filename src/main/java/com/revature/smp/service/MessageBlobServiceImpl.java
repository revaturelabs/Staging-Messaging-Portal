package com.revature.smp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.smp.beans.MessageBlob;
import com.revature.smp.dao.MessageBlobDAO;

import java.util.List;

import javax.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class MessageBlobServiceImpl implements MessageBlobService {
	
	@Autowired
	MessageBlobDAO mdao;
	

	@Override
	public List<MessageBlob> getMostRecentBlob(int messageRoomId) {
		return mdao.getMostRecent(messageRoomId);
	}

	@Override
	public List<MessageBlob> getPreviousBlob(int messageRoomId,int messageBlobId) {
		return mdao.getPrevious(messageRoomId, messageBlobId);
	}

}
