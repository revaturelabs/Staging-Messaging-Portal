package com.revature.smp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping( value = "/list-usernames", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public List<String> getUsernames() {
		List<String> usernames = null; // List of users to be returned at the end of the method
		
		usernames = userService.getUsernames();
		
		System.err.println(usernames);
		
		return usernames;
	}
	
	@RequestMapping( value = "/list-users/{username}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE )
	public List<User> getUsers(@PathVariable String username) {
		List<User> users = null; // List of users to be returned at the end of the method
		
		User user = userService.getByUsername(username);
		String roleName = (user != null && user.getRole() != null) ? user.getRole().getRoleName() : "";
		
		if(roleName.equals("manager")){
			users = userService.getAllUsers();
		}
		
		System.err.println(users);
		
		return users;
	}
	
	
}
