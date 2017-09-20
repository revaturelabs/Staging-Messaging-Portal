package com.revature.smp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.smp.beans.Message;
import com.revature.smp.repo.MessageCacheRepository;
import com.revature.smp.repo.MessageRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageRepository msgRepo;
	
	@Autowired
	MessageCacheRepository cacheRepo;
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public boolean postMessage(Message message)
	{
		return (msgRepo.save(message) != null) ? true : false;
	}

	@Override
	public List<Message> getMessagesByRoomId(int roomId) {
		return msgRepo.findByRoomId(roomId);
	}

}
