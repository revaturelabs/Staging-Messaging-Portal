package com.revature.smp.service;

import java.util.List;

import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageCache;

public interface MessageService {
	
	List<MessageCache> getPrevious(int roomId);
	
	List<Message> getUpdate(int roomId);
	
	boolean postMessage(int roomId, Message message);
	
}
