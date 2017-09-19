package com.revature.smp.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping( value = "/{Username}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)

	public User  Username(@PathVariable String Username ) {
		User user = userrepo.findByUsername(Username);
		return user;
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
