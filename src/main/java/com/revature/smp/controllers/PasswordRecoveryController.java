package com.revature.smp.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.User;
import com.revature.smp.domain.PasswordRecoveryRequest;
import com.revature.smp.services.PasswordRecoveryServiceImpl;

@RestController
@RequestMapping(value = "/api")
public class PasswordRecoveryController {
	
	PasswordRecoveryServiceImpl recover;
	
	public static final String RECOVER_USER_URL = "/password-recovery";
	
	@RequestMapping(value = RECOVER_USER_URL, method = RequestMethod.POST, consumes="application/json")
	public void recoverPass(@RequestBody PasswordRecoveryRequest request, HttpServletResponse response) {
		System.out.println("Email is: " + request.getEmail());
		User user = new User();
		
		try {
			user = recover.getRegisteredUser(request.getEmail());
			
			if (user != null) {
				recover.updateUserPassword(user);
				
				user.setPassword(recover.generateTemporaryPassword());
			} else {
				//throw custom exception that user is not found -- need to create exception
				//this will be then handled by front end app to notify user
				System.out.print("User not found");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			//throw custom exception that user is not found -- need to create exception
			//this will be then handled by front end app to notify user
			System.out.print("User not found");
		}
		

		
	}
}
