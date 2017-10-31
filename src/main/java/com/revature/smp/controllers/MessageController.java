package com.revature.smp.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * User is identified by the cookie supplied, and matched with the logged in users in the session.
	 * @param message: The message that the user wishes to post.
	 * @param WhosCookie: a cookie just holding the username.
	 * @return Http status code:
	 * UNAUTHORIZED: user did not send the YonToken cookie or they are not logged in.
	 * INTERNAL_SERVER_ERROR: The system doesn't know how to add messages to the database.
	 * CREATED: Message successfully added.
	 * NO_CONTENT: Message not added for no good reason.
	 */
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public void postMessage(@RequestBody Message message, @CookieValue("YonToken") String WhosCookie, HttpServletResponse response) 
	{
		User user = (User) session.getAttribute(WhosCookie);
		if(user == null)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
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
	public ResponseEntity<List<Message>> fetchMessagesByRoomId(@PathVariable Integer roomId, HttpServletResponse response)
	{
		List<Message> messageList = new ArrayList<Message>();		
		messageList = msgSvc.getMessagesByRoomId(roomId);		
		return new ResponseEntity<List<Message>>(messageList,HttpStatus.OK);
	}
	/**
	 * Pulls the public messages and puts them in a list.
	 * @return OK: The list in json form.
	 * INTERNAL_SERVER_ERROR: Someone doesn't know how to write good hibernate.
	 */
	@RequestMapping(value="/fetch-room/public", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> fetchPublicMessages(HttpServletResponse response)
	{
		List<Message> messageList = new ArrayList<Message>();		
		try
		{
			messageList = msgSvc.getMessagesByRoomName("public");
		}
		catch (SQLException e)
		{
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
	}
	/**
	 * Pulls the private messages from the private room. (Not sure how this will work for the manager)
	 * @param WhosCookie Will hold the username.
	 * @param response 
	 * @return OK: The json form of the private messages.
	 * UNAUTHORIZED: user has not supplied YonToken cookie or is not logged in.
	 * INTERNAL_SERVER_ERROR: Someone doesn't know how to write good hibernate.
	 * NO_CONTENT: This user has no private room.
	 */
	@RequestMapping(value="/fetch-room/private", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Message>> fetchPrivateMessages(@CookieValue("YonToken") String WhosCookie, HttpServletResponse response)
	{
		User user = (User) session.getAttribute(WhosCookie);
		if(user == null)
		{
			return new ResponseEntity<List<Message>>(HttpStatus.UNAUTHORIZED);
		}
		List<Message> messageList = new ArrayList<Message>();		
		String privRoomName = (user.getUsername() != null && !user.getUsername().isEmpty() 
				? ("priv_"+user.getUsername()) : null);		
		if (privRoomName != null)
		{
			try
			{
				messageList = msgSvc.getMessagesByRoomName(privRoomName);
			}
			catch (SQLException e)
			{
				return new ResponseEntity<List<Message>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Message>>(messageList, HttpStatus.OK);
	}
}