package com.revature.smp.Security;


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
public class UserQueryController  {
	
	@Autowired
	
	UserRepository userrepo;
//	
//	@RequestMapping("/hi")
//	public String index() {
//		return "Greetings from Spring Boot!";
//	}
	
	@RequestMapping( value = "/access", method=RequestMethod.POST)
	
	public ResponseEntity<User>  Username(@RequestBody  User username ) {
//		System.err.println(username);
		User user = userrepo.findByUsername(username.getUsername());
		System.err.println(user.getUsername());

		if (user != null &&
			username.getUsername().equals(user.getUsername()) &&
			username.getPassword().equals(user.getPassword()) &&
			user.getActive().equals("y")){
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
	}
	
//	@RequestMapping( value = "/password/{Username}", method=RequestMethod.GET,
//			produces=MediaType.APPLICATION_JSON_VALUE)
//	
//	public String Password(@PathVariable String Username ) {
//		User user = userrepo.findByUsername(Username);
//		System.out.println(user);
//		return user.getPassword();
//	}
//	
}
