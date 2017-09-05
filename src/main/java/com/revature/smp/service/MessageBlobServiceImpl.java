package com.revature.smp.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageBlob;
import com.revature.smp.dao.MessageBlobDAO;

@org.springframework.stereotype.Service
@Transactional
public class MessageBlobServiceImpl implements MessageBlobService {
	
	@Autowired
	MessageBlobDAO mdao;
	
	@Override
	public List<MessageBlob> getMostRecent(int messageRoomId) {
		return mdao.getMostRecent(messageRoomId);
	}
	
	@Override
	public List<MessageBlob> getPrevious(int messageRoomId, int messageBlobId) {
		return mdao.getPrevious(messageRoomId, messageBlobId);
	}
	
	@Override
	public List<MessageBlob> getUpdate(int messageRoomId, int messageBlobId) {
		return mdao.getUpdate(messageRoomId, messageBlobId);
	}
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void postMessage(int messageRoomId, String user, String text) {
		
		ObjectMapper om = new ObjectMapper();
		List<MessageBlob> mr = getMostRecent(messageRoomId);
		MessageBlob b = null;
		if (!mr.isEmpty()) {
			b = mr.get(0);
		} else {
			b = new MessageBlob((int) mdao.count() + 1, messageRoomId);
		}
		try {
			Message[] m = om.readValue(b.getMessageBlob(), Message[].class);
			if (m.length >= 25) {
				b = new MessageBlob((int) mdao.count() + 1, messageRoomId);
				m = om.readValue(b.getMessageBlob(), Message[].class);
			}
			Message newm = new Message(user, System.currentTimeMillis(), text);
			
			List<Message> lm = new LinkedList<>();
			for (Message ms : m) {
				lm.add(ms);
			}
			lm.add(newm);
			
			b.setMessageBlob(om.writeValueAsBytes(lm));
			
			mdao.save(b);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	
}
