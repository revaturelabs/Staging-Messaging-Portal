package com.revature.smp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageRoom;
import com.revature.smp.repo.MessageCacheRepository;
import com.revature.smp.repo.MessageRepository;
import com.revature.smp.repo.MessageRoomRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageRepository msgRepo;
	
	@Autowired
	MessageRoomRepository msgRoomRepo;
	
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
	public List<Message> getMessagesByRoomId(int roomId) 
	{
		Optional<MessageRoom> optionals = msgRoomRepo.findById(roomId);
		
		if (optionals.get() != null)
		{
			return optionals.get().getMessages();
		}
		
		return null;
	}

	@Override
	public List<Message> getMessagesByRoomName(String roomName) 
	{
		Optional<MessageRoom> optionals = null;
		
		if (roomName.toLowerCase().equals("public"))
		{
			optionals = msgRoomRepo.findById(1);
		}
		
		if (optionals.get() != null)
		{
			return optionals.get().getMessages();
		}
		
		return null;
	}


}
