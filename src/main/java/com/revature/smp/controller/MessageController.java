package com.revature.smp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.revature.smp.beans.MessageClob;
import com.revature.smp.service.MessageService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	// @Autowired
	// UserService userSvc;
	
	@Autowired
	MessageService msgSvc;
	
	@RequestMapping(value="/fetch-update/{room}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageClob>> getMostRecent(@PathVariable Integer room) {
		return new ResponseEntity<List<MessageClob>>(msgSvc.getMostRecent(room), HttpStatus.OK);
	}
	
	@RequestMapping(value="/fetch-update/{room}/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageClob>> getUpdate(
			@PathVariable Integer room, @PathVariable Integer id) {
		return new ResponseEntity<List<MessageClob>>(msgSvc.getUpdate(room, id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/fetch-previous/{room}/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageClob>> getPrevious(
			@PathVariable Integer room, @PathVariable Integer id) {
		return new ResponseEntity<List<MessageClob>>(msgSvc.getPrevious(room, id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/post/{room}", method=RequestMethod.POST)
	public Map<String, Object> postMessage(@PathVariable Integer room, @RequestBody Message message) 
	{
		boolean success = false;
		while (!success) 
		{
			try {
				msgSvc.postMessage(message);
				success = true;
			} catch (Exception e) {
				
			}
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("response", HttpStatus.OK);
		return responseMap;
	}
	
	@RequestMapping(value="/cache/{room}", method=RequestMethod.POST)
	public Map<String, Object> postCache(@PathVariable Integer room, @RequestBody Message message)
	{
		boolean success = false;
		while (!success) 
		{
			try {
				msgSvc.cacheMessages(room, message.getUsername(), message.getText());
				success = true;
			} catch (Exception e) {
				
			}
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("response", HttpStatus.OK);
		return responseMap;
	}
	
}