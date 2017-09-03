package com.revature.smp.controller;

import java.io.IOException;
import java.util.HashMap;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.smp.beans.MessageBlob;
import com.revature.smp.beans.MessageBean;
import com.revature.smp.service.MessageBlobService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	@Autowired
	MessageBlobService ms;
	
    @RequestMapping(value="/getmostrecent/{room}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageBlob> getMostRecent(@PathVariable Integer room) {
        MessageBlob m = ms.getMostRecentBlob(room);
        return new ResponseEntity<MessageBlob>(m, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getprevious/{room},{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageBlob> getPrevious(@PathVariable Integer room, @PathVariable Integer id){
    	MessageBlob m = ms.getPreviousBlob(room, id);
    	return new ResponseEntity<MessageBlob>(m,HttpStatus.OK);
    }
	
	//@Autowired
	//UserDAO UsersService;

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public Map<String, Object> quickSaveAssessment(@RequestBody MessageBean message)
			throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("status", "200");
		responseMap.put("theMessage", message);
		
		System.out.println(message.toString());
		
		return responseMap;
	}

}