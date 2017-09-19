package com.revature.smp.services;

import java.util.List;

import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageCache;

public interface MessageService {
	
	public boolean postMessage(Message message);
	
	List<MessageCache> getPrevious(int roomId);
	
	List<Message> getUpdate(int roomId);
	
}
