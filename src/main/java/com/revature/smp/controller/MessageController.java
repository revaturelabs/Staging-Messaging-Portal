package com.revature.smp.controller;

import java.util.Date;
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

import com.revature.smp.beans.MessageClob;
import com.revature.smp.beans.Message;
import com.revature.smp.service.MessageClobService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	// @Autowired
	// UserService userSvc;
	
	@Autowired
<<<<<<< HEAD
	MessageBlobService msgBlobSvc;
	
	@RequestMapping(value="/fetch-update/{room}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageBlob>> getMostRecent(@PathVariable Integer room) {
		
		/* fake message list test */
		ResponseEntity<List<Message>> messages = new ResponseEntity<List<Message>>(null);
		
		return new ResponseEntity<List<MessageBlob>>(msgBlobSvc.getMostRecent(room), HttpStatus.OK);
	}
	
	@RequestMapping(value="/fetch-update/{room}/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageBlob>> getUpdate(
			@PathVariable Integer room, @PathVariable Integer id) {
		return new ResponseEntity<List<MessageBlob>>(msgBlobSvc.getUpdate(room, id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/fetch-previous/{room}/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageBlob>> getPrevious(
			@PathVariable Integer room, @PathVariable Integer id) {
		return new ResponseEntity<List<MessageBlob>>(msgBlobSvc.getPrevious(room, id), HttpStatus.OK);
=======
	MessageClobService ms;
	
	@RequestMapping(value = "/getmostrecent/{room}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageClob>> getMostRecent(
			@PathVariable Integer room) {
		return new ResponseEntity<List<MessageClob>>(ms.getMostRecent(room),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getprevious/{room}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageClob>> getPrevious(
			@PathVariable Integer room, @PathVariable Integer id) {
		return new ResponseEntity<List<MessageClob>>(ms.getPrevious(room, id),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getupdate/{room}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MessageClob>> getUpdate(
			@PathVariable Integer room, @PathVariable Integer id) {
		return new ResponseEntity<List<MessageClob>>(ms.getUpdate(room, id),
				HttpStatus.OK);
>>>>>>> ae3190acaabd5e9e305bf09fe62c188c710384da
	}
	
	@RequestMapping(value="/post/{room}", method=RequestMethod.POST)
	public Map<String, Object> postMessage(@PathVariable Integer room, @RequestBody Message message) 
	{
		boolean success = false;
<<<<<<< HEAD
		while (!success) 
		{
			try {
				msgBlobSvc.postMessage(room, message.getUser(), message.getText());
				success = true;
			} catch (Exception e) {
				
			}
=======
		while (!success) {
			
			ms.postMessage(room, message.getUser(), message.getText());
			success = true;
			
>>>>>>> ae3190acaabd5e9e305bf09fe62c188c710384da
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("response", HttpStatus.OK);
		return responseMap;
	}
	
}