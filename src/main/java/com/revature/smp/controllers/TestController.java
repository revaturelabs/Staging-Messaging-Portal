package com.revature.smp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sam on 8/31/2017.
 */
@RestController
public class TestController {
	
	@RequestMapping(value = "/getproduct/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> getProduct(@PathVariable Integer id) {
		Integer p = id;
		
		return new ResponseEntity<Integer>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/junk", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> junk() {
		return new ResponseEntity<String>("help plz", HttpStatus.OK);
	}
}
