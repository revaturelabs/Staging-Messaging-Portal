package com.revature.smp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class MvcConfig.
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("greeting.html");
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
	}
	
}