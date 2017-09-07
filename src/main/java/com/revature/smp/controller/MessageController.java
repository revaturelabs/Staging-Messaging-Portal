package com.revature.smp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.MessageBlob;
import com.revature.smp.beans.Message;
import com.revature.smp.service.MessageBlobService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	@Autowired
	MessageBlobService ms;
	
    @RequestMapping(value="/getmostrecent/{room}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageBlob>> getMostRecent(@PathVariable Integer room) {
        List<MessageBlob> m = ms.getMostRecent(room);
        
        return new ResponseEntity<List<MessageBlob>>(m, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getprevious/{room}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageBlob>> getPrevious(@PathVariable Integer room, @PathVariable Integer id){
    	List<MessageBlob> m = ms.getPrevious(room, id);
    	
    	return new ResponseEntity<List<MessageBlob>>(m,HttpStatus.OK);
    }
    
    @RequestMapping(value="/getupdate/{room}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageBlob>> getUpdate(@PathVariable Integer room, @PathVariable Integer id){
    	List<MessageBlob> m = ms.getUpdate(room, id);
    	
    	return new ResponseEntity<List<MessageBlob>>(m,HttpStatus.OK);
    }
	
	//@Autowired
	//UserDAO UsersService;

	@RequestMapping(value = "/post/{room}", method = RequestMethod.POST)
	public Map<String, Object> quickSaveAssessment(@PathVariable Integer room,@RequestBody Message message){
		boolean success = false;
		while(!success) {
			try {
				ms.postMessage(room, message.getUser(), message.getText());
				success = true;
			}catch(JpaSystemException E) {
				System.err.println(E);
			}
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("status", "200");
		return responseMap;
	}

}