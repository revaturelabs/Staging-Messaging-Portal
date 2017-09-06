package com.revature.smp.controllers;

import java.sql.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.smp.beans.Location;
import com.revature.smp.beans.Role;
import com.revature.smp.beans.Status;
import com.revature.smp.beans.User;
import com.revature.smp.domain.UserRegistrationRequest;
import com.revature.smp.services.registration.AssociateRegistrationService;
import com.revature.smp.services.registration.RegistrationManagerService;

@RestController
public class RegistrationController {
	
	public static final String RESTON = "Reston";
	public static final String FLORIDA = "Florida";
	public static final String NEW_YORK = "New York";
	public static final String REGISTER_USER_URL = "/register-user";
	
	@Autowired
	AssociateRegistrationService registrationService;
	
	@Autowired
	RegistrationManagerService managerService;
	
	/**
	 * 
	 * @param UserRegistrationRequest object created from consuming JSON
	 * @return 201 - successfully created new user
	 * 		   409 - user already exists with requested email
	 * 		   501 - service unavailable (trouble connecting to database)
	 */
	@RequestMapping(value = REGISTER_USER_URL, method = RequestMethod.POST, consumes="application/json")
	public void register(@RequestBody UserRegistrationRequest request, HttpServletResponse response) 
	{

		User userRegistration = new User(request.getFirstName(), request.getLastName(),
				request.getEmail(), request.getLocationId());
		
		try {
			registrationService.registerAssociate(userRegistration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(request.toString());
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value="/all-pending-users", method = RequestMethod.GET)
	public String getAllPendingUsers() {
		System.out.println(managerService.getRegisteringUsers());
		return null;
	}
	
	
/*	@RequestMapping("/approve-registration")
	
	@RequestMapping("/deny-registration")
	
	@RequestMapping("/notify-manager")
	
	@RequestMapping("/notify-associate")*/
	

}
