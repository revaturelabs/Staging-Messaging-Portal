package com.revature.smp.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.User;
import com.revature.smp.services.UserService;

@RestController
@RequestMapping("/logger")
public class UserLoginController  {
	
	@Autowired
	
	UserService userService;
	
	@RequestMapping( value = "/access", method=RequestMethod.POST)
	
	public ResponseEntity<User>  Username(@RequestBody  User username ) {
		User user = userService.getByUsername(username.getUsername());
		if (user != null &&
			username.getUsername().equals(user.getUsername()) &&
			username.getPassword().equals(user.getPassword()) &&
			user.getActive().equals("y")){
			
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@RequestMapping( value = "/list-users", method=RequestMethod.GET)
	public List<User> getUsers() {
		
		return null;
	}
	
	
}
