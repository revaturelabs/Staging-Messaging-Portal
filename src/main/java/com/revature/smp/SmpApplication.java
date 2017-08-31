package com.revature.smp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.revature.smp.services.registration.AssociateRegistrationService;


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.revature.smp.dao")
public class SmpApplication {
	
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
	
	
	// @Bean
	// public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	// return args -> {
	//
	// System.out.println(
	// "Let's inspect the beans provided by Spring Boot:");
	//
	// String[] beanNames = ctx.getBeanDefinitionNames();
	// Arrays.sort(beanNames);
	// for (String beanName : beanNames) {
	// System.out.println(beanName);
	// }
	//
	// };
	// }
}
