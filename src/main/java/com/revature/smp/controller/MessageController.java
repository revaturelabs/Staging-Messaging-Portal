package com.revature.smp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glassfish.jersey.server.spi.ResponseErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.Message;
import com.revature.smp.beans.MessageCache;
import com.revature.smp.service.MessageService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	// @Autowired
	// UserService userSvc;
	
	@Autowired
	MessageService msgSvc;
	
	@RequestMapping(value="/fetch-update/{roomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> getUpdate(
			@PathVariable Integer roomId) {
		return new ResponseEntity<List<Message>>(msgSvc.getUpdate(roomId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/fetch-previous/{roomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageCache>> getPrevious(
			@PathVariable Integer roomId) {
		return new ResponseEntity<List<MessageCache>>(msgSvc.getPrevious(roomId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/post/{roomId}", method=RequestMethod.POST)
	public Map<String, Object> postMessage(@PathVariable Integer roomId, @RequestBody Message message) 
	{
		boolean success = false;
		while (!success) 
		{
			try {
				success = msgSvc.postMessage(roomId, message);
			} catch (Exception e) {
				
			}
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		if (success)
			responseMap.put("response", HttpStatus.OK);
		else
			responseMap.put("response", HttpStatus.I_AM_A_TEAPOT);
		
		return responseMap;
	}
	
}