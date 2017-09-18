package com.revature.smp.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageClob;
import com.revature.smp.dao.MessageBlobDAO;

@Service
@Transactional
public class MessageBlobServiceImpl implements MessageBlobService {
	
	@Autowired
	MessageBlobDAO mdao;
	
	@Override
	public List<MessageClob> getMostRecent(int messageRoomId) {
		List<MessageClob> cs1 = mdao.getMostRecent(messageRoomId);
		return cs1;
	}
	
	@Override
	public List<MessageClob> getPrevious(int messageRoomId, int messageBlobId) {
		return mdao.getPrevious(messageRoomId, messageBlobId);
	}
	
	@Override
	public List<MessageClob> getUpdate(int messageRoomId, int messageBlobId) {
		return mdao.getUpdate(messageRoomId, messageBlobId);
	}
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void postMessage(int messageRoomId, String user, String text) {
		
		
		
	}
}
