package com.revature.smp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.User;
import com.revature.smp.repo.UserRepository;

@RestController
@RequestMapping("/logger")
public class UserLoginController  {
	
	@Autowired
	
	UserRepository userrepo;
	
	@RequestMapping( value = "/access", method=RequestMethod.POST)
	
	public ResponseEntity<User>  Username(@RequestBody  User username ) {
		User user = userrepo.findByUsername(username.getUsername());
		if (user != null &&
			username.getUsername().equals(user.getUsername()) &&
			username.getPassword().equals(user.getPassword()) &&
			user.getActive().equals("y")){
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
	}
	
}
