package com.revature.smp.Security;


import org.springframework.beans.factory.annotation.Autowired;
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
	
	public User  Username(@RequestBody  User username ) {
//		System.err.println(username);
		User user = userrepo.findByUsername(username.getUsername());
		System.err.println(user.getUsername());

		if (user != null){
			if (!(username.getUsername().equals(user.getUsername())) && !(username.getPassword().equals(user.getPassword())))
					{
				
				System.out.println("who you?");
	
				
			}
			System.out.println("you good ?");

			return user;
		}
		return null;
		
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
