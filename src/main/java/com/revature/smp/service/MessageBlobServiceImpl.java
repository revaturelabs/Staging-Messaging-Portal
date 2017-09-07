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
		
		ObjectMapper om = new ObjectMapper();
		List<MessageClob> mr = getMostRecent(messageRoomId);
		MessageClob b = null;
		if (!mr.isEmpty()) {
			b = mr.get(0);
		} else {
			b = new MessageClob((int) mdao.count() + 1, messageRoomId);
		}
		try {
			Message[] m = om.readValue(b.getMessageBlob(), Message[].class);
			if (m.length >= 25) {
				b = new MessageClob((int) mdao.count() + 1, messageRoomId);
				m = om.readValue(b.getMessageBlob(), Message[].class);
			}
			Message newm = new Message(user, System.currentTimeMillis(), text);
			
			List<Message> lm = new LinkedList<>();
			for (Message ms : m) {
				lm.add(ms);
			}
			lm.add(newm);
			
			// b.setMessageBlob(om.writeValueAsBytes(lm));
			
			mdao.save(b);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
}
