package com.revature.smp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class HelloController, a test REST controller
 */
@RestController
public class HelloController {
	/**
	 * Rest endpoint for this controller
	 *
	 * @return the string
	 */
	@RequestMapping("/restTest")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
