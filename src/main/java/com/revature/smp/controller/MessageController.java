package com.revature.smp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.smp.beans.MessageBean;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
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