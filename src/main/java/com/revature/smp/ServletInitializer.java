package com.revature.smp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The Class ServletInitializer, loads up the dispatcher for hosting endpoints
 */
public class ServletInitializer extends SpringBootServletInitializer {
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * 		org.springframework.boot.web.servlet.support.SpringBootServletInitializer
	 *      #configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(SmpApplication.class);
	}
	
}
