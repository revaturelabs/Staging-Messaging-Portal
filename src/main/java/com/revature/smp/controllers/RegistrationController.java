package com.revature.smp.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.User;
import com.revature.smp.beans.UserRegistrationRequest;
import com.revature.smp.exceptions.UsernameExistsException;
import com.revature.smp.services.RegistrationService;

@RestController
@RequestMapping(value = "/api")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	/**
	 * @param UserRegistrationRequest object created from consuming JSON
	 * @return 201 - successfully created new user
	 * 		   409 - user already exists with requested email
	 * 		   501 - service unavailable (trouble connecting to database)
	 */
	@RequestMapping(value = "/register-user", method = RequestMethod.POST, consumes="application/json")
	public void register(@RequestBody UserRegistrationRequest request, HttpServletResponse response) 
	{
		User userRegistration = new User(request.getFirstName(), request.getLastName(),
				request.getEmail(), request.getLocationId());		
		try
		{
			registrationService.registerAssociate(userRegistration);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (UsernameExistsException e)
		{
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.addHeader("error", e.toString());
		}
	}
	
	@RequestMapping(value="/all-pending-users", method = RequestMethod.GET)
	public String getAllPendingUsers()
	{
		// TODO make this do something useful…
		System.out.println(registrationService.getRegisteringUsers());
		return null;
	}
	
	
/*	@RequestMapping("/approve-registration")
	
	@RequestMapping("/deny-registration")
	
	@RequestMapping("/notify-manager")
	
	@RequestMapping("/notify-associate")*/
	

}
