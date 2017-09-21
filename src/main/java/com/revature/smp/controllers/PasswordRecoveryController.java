package com.revature.smp.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.User;
import com.revature.smp.domain.PasswordRecoveryRequest;
import com.revature.smp.services.PasswordRecoveryService;

@RestController
@RequestMapping(value = "/api")
public class PasswordRecoveryController {
	
	PasswordRecoveryService recover;
	
	public static final String RECOVER_USER_URL = "/recover-password";
	
	@RequestMapping(value = RECOVER_USER_URL, method = RequestMethod.POST, consumes="application/json")
	public void recoverPass(@RequestBody PasswordRecoveryRequest request, HttpServletResponse response) {
		User user = recover.getRegisteredUser(request.getEmail());
		
		user.setPassword(recover.generateTemporaryPassword());
		System.out.println(user.getEmail());
		try {
			recover.updateUserPassword(user);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
