package com.revature.smp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	
	/**
	 * 
	 * @param request
	 * @return 201 - successfully created new user
	 * 		   409 - user already exists with requested email
	 * 		   501 - service unavailable (trouble connecting to database)
	 */
	@RequestMapping(value ="/register", method = RequestMethod.POST, consumes="application/json")
	public String register(HttpServletRequest request, HttpServletResponse response) 
	{
		
		return null;
	}
	
/*	@RequestMapping("/approve-registration")
	
	@RequestMapping("/deny-registration")
	
	@RequestMapping("/notify-manager")
	
	@RequestMapping("/notify-associate")*/
	

}
