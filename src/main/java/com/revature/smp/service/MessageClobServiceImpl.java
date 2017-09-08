package com.revature.smp.service;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.smp.beans.ApplicationContextProvider;
import com.revature.smp.beans.MessageClob;
import com.revature.smp.dao.MessageClobDAO;

@Service
@Transactional
public class MessageClobServiceImpl implements MessageClobService {
	
	@Autowired
	MessageClobDAO mdao;
	
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
		DataSource ds = (DataSource) ApplicationContextProvider
				.getApplicationContext().getBean("dataSource");
		// Connection d = DataSourceUtils.getConnection(ds);
		Connection c;
		try {
			c = ds.getConnection();
			Clob clob = c.createClob();
			clob.setString(1, "Test string");
			System.out.println(clob);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ObjectMapper om = new ObjectMapper();
		// List<MessageClob> mr = getMostRecent(messageRoomId);
		// MessageClob b = null;
		// if (!mr.isEmpty()) {
		// b = mr.get(0);
		// } else {
		// b = new MessageClob((int) mdao.count() + 1, messageRoomId);
		// }
		// try {
		// Message[] m = om.readValue(b.getMessageClob(), Message[].class);
		// if (m.length >= 25) {
		// b = new MessageClob((int) mdao.count() + 1, messageRoomId);
		// m = om.readValue(b.getMessageClob(), Message[].class);
		// }
		// Message newm = new Message(user, System.currentTimeMillis(), text);
		//
		// List<Message> lm = new LinkedList<>();
		// for (Message ms : m) {
		// lm.add(ms);
		// }
		// lm.add(newm);
		//
		// // b.setMessageClob(om.writeValueAsBytes(lm));
		//
		// mdao.save(b);
		// } catch (IOException e) {
		// System.err.println(e);
		// }
		
	}
}
