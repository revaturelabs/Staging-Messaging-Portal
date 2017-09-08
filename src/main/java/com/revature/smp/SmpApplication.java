package com.revature.smp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


/**
 * The Class SmpApplication.
 */
@SpringBootApplication
@ComponentScan("com.revature.*")
@EnableScheduling
@EnableJpaRepositories("com.revature.smp.dao")
public class SmpApplication {
	
	/**
	 * The main method, runs Spring Boot
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		SpringApplication.run(SmpApplication.class, args);
	}
	
	/**
	 * Rest template, builds rest calls.
	 *
	 * @param builder
	 *            the builder
	 * @return the rest template
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
		return builder.build();
	}

}
