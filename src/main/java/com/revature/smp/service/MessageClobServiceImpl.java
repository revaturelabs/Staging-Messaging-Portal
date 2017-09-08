package com.revature.smp.service;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.smp.beans.MessageClob;
import com.revature.smp.dao.MessageClobDAO;

@Service
@Transactional
public class MessageClobServiceImpl implements MessageClobService {
	
	@Autowired
	MessageClobDAO mdao;
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public List<MessageClob> getMostRecent(int messageRoomId) {
		List<MessageClob> cs1 = mdao.getMostRecent(messageRoomId);
		return cs1;
	}
	
	@Override
	public List<MessageClob> getPrevious(int messageRoomId, int messageClobId) {
		return mdao.getPrevious(messageRoomId, messageClobId);
	}
	
	@Override
	public List<MessageClob> getUpdate(int messageRoomId, int messageClobId) {
		return mdao.getUpdate(messageRoomId, messageClobId);
	}
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void postMessage(int messageRoomId, String user, String text) {
		DataSource ds = (DataSource) context.getBean("dataSource");
		Connection c;
		String message = user + " : " + text;
		try {
			c = ds.getConnection();
			Clob clob = c.createClob();
			clob.setString(messageRoomId, message);
			System.out.println(clob.getSubString(1, message.length()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
