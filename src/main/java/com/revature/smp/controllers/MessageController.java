package com.revature.smp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.Message;
import com.revature.smp.services.MessageService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	@Autowired
	MessageService msgSvc;
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public Map<String, Object> postMessage(@RequestBody Message message) 
	{
		boolean success = false;
		while (!success) 
		{
			try {
				message.setTime(new Date());
				
				success = msgSvc.postMessage(message);
			} catch (Exception e) {
				System.out.println("frak... post failed");
			}
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		if (success)
			responseMap.put("response", HttpStatus.OK);
		else
			responseMap.put("response", HttpStatus.I_AM_A_TEAPOT);
		
		return responseMap;
	}
	
	@RequestMapping(value="/fetch-room/{roomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> fetchMessagesByRoomId(@PathVariable Integer roomId)
	{
		List<Message> messageList = new ArrayList<Message>();
		
		messageList = msgSvc.getMessagesByRoomId(roomId);
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("messages", messageList);
		
		return responseMap;
	}
	
	@RequestMapping(value="/fetch-room/public", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> fetchPublicMessages()
	{
		List<Message> messageList = new ArrayList<Message>();
		
		messageList = msgSvc.getMessagesByRoomName("public");
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("messages", messageList);
		
		return responseMap;
}
	
}