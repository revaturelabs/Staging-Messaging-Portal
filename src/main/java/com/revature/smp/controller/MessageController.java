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

/**
 * The Class MessageController, for accepting message to be saved to database
 * Mapped to "/msg"
 */
@RestController
@RequestMapping("/msg")
public class MessageController {
	
	// @Autowired
	// UserDAO UsersService;
	
	/**
	 * Save message via Post
	 *
	 * @param message
	 *            the message
	 * @return the map
	 * @throws JsonParseException
	 *             the json parse exception
	 * @throws JsonMappingException
	 *             the json mapping exception
	 * @throws JsonProcessingException
	 *             the json processing exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public Map<String, Object> saveMessage(@RequestBody MessageBean message)
			throws JsonParseException, JsonMappingException,
			JsonProcessingException, IOException {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("status", "200");
		responseMap.put("theMessage", message);
		
		System.out.println(message.toString());
		
		return responseMap;
	}
	
}