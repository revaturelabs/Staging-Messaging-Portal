package com.revature.smp.controllers;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.MessageRoom;
import com.revature.smp.beans.User;
import com.revature.smp.repo.UserRepository;

@RestController
@RequestMapping("/logger")
public class UserLoginController  {
	
	@Autowired	
	UserRepository userrepo;
	
	@Autowired
	HttpSession session;
	/**
	 * Basic method for logging in a user and pull their rooms from the database.
	 * Adds the username to the session and also adds a cookie to the response.
	 * This cookie should be checked when pulling information from the database.
	 * @param username A User object in json form that only needs the username and password fields.
	 * @return a List of all the message rooms that the user is part of.
	 */
	@RequestMapping( value = "/access", method=RequestMethod.POST)	
	public ResponseEntity<List<MessageRoom>>  Username(@RequestBody  User username, HttpServletResponse response)
	{
		if(session.getAttribute(username.getUsername()) == null)
		{
			User user = userrepo.findByUsername(username.getUsername());
			if (user != null && username.getUsername().equals(user.getUsername())
					&& username.getPassword().equals(user.getPassword()) && user.getActive().equals("y"))
			{
				session.setAttribute(user.getUsername(), user);
				response.addCookie(new Cookie("YonToken", user.getUsername()));
				return new ResponseEntity<List<MessageRoom>>(user.getMessageRooms(), HttpStatus.ACCEPTED);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);	
			}
		}
		else
		{
			User user = (User) session.getAttribute(username.getUsername());
			return new ResponseEntity<List<MessageRoom>>(user.getMessageRooms(), HttpStatus.ACCEPTED);
		}
	}	
}