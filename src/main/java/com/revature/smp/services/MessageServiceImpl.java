package com.revature.smp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageCache;
import com.revature.smp.dao.MessageCacheDAO;
import com.revature.smp.dao.MessageDAO;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDAO msgDao;
	
	@Autowired
	MessageCacheDAO cacheDao;
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public List<MessageCache> getPrevious(int roomId) {
		return cacheDao.getPrevious(roomId);
	}
	
	@Override
	public List<Message> getUpdate(int roomId) {
		return msgDao.getUpdate(roomId);
	}
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public boolean postMessage(int roomId, Message message)
	{
		return msgDao.saveMessageByRoomId(roomId, message);
	}

}
