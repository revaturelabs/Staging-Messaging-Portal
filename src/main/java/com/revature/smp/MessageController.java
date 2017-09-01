package com.revature.smp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.Message;
import com.revature.smp.service.MessageService;

@RestController
public class MessageController {
	@Autowired
	MessageService ms;
	
    @RequestMapping(value="/getmostrecent/{room}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> getMostRecent(@PathVariable Integer room) {
        Message m = ms.getMostRecentBlob(room);
        return new ResponseEntity<Message>(m, HttpStatus.OK);
    }
    
    @RequestMapping(value="/getprevious/{room},{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> getPrevious(@PathVariable Integer room, @PathVariable Integer id){
    	Message m = ms.getPreviousBlob(room, id);
    	return new ResponseEntity<Message>(m,HttpStatus.OK);
    }
    
    //@RequestMapping(value="/postmessage/{room}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    //public ResponseEntity
}