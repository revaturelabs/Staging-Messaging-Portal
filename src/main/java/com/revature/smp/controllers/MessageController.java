package com.revature.smp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.Message;
import com.revature.smp.beans.User;
import com.revature.smp.services.MessageService;
import com.revature.smp.services.UserService;

@RestController
@RequestMapping("/msg")
public class MessageController {
	
	@Autowired
	MessageService msgSvc;
	
	@Autowired
	UserService userSvc;
	
	@Autowired
	HttpSession session;
	/**
	 * In order to post a new message, the user must be logged on.
	 * User is identified by the cookie the supply, and matched with the logged in users in the session.
	 * @param message: The message that the user wishes to post.
	 * @param WhosCookie: a cookie just holding the username.
	 * @return
	 */
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public void postMessage(@RequestBody Message message, @CookieValue("YonToken") String WhosCookie, HttpServletResponse response) 
	{
		boolean success = false;
		while (!success) 
		{
			try
			{
				message.setTime(new Date());				
				success = msgSvc.postMessage(message);
			}
			catch (Exception e)
			{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}				
		if (success)
		{
			response.setStatus(HttpServletResponse.SC_CREATED);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}
	/**
	 * Pulls all the messages from the specified room.
	 * @param roomId: The room to pull from.
	 * @param response: For better control over the response.
	 * @return A list of messages.
	 */
	@RequestMapping(value="/fetch-room/{roomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> fetchMessagesByRoomId(@PathVariable Integer roomId,HttpServletResponse response)
	{
		List<Message> messageList = new ArrayList<Message>();		
		messageList = msgSvc.getMessagesByRoomId(roomId);		
		return new ResponseEntity<List<Message>>(messageList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/fetch-room/public", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> fetchPublicMessages()
	{
		List<Message> messageList = new ArrayList<Message>();
		
		try {
			messageList = msgSvc.getMessagesByRoomName("public");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("messages", messageList);
		
		return responseMap;
	}

	@RequestMapping(value="/fetch-room/private", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> fetchPrivateMessages(@RequestBody User user)
	{
		List<Message> messageList = new ArrayList<Message>();
		
		System.out.println(user);
		
		String privRoomName = (user != null && user.getUsername() != null
				&& !user.getUsername().isEmpty() 
				? ("priv_"+user.getUsername()) : null);
		
		if (privRoomName != null)
		{
			try {
				messageList = msgSvc.getMessagesByRoomName(privRoomName);
			} catch (SQLException e) {
				System.out.println("frak this message private controller fetch thing");
				e.printStackTrace();
			}
		}
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("messages", messageList);
		
		return responseMap;
	}
	
}