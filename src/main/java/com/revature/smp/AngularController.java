package com.revature.smp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AngularController {
	
	@RequestMapping("/api/hi")
	public String hi() {
		return "Hello World from Restful API";
	}
}

