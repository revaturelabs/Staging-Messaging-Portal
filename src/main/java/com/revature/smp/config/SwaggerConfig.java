package com.revature.smp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket smpApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.revature.smp.controllers"))
                .paths(postPaths())
                .build()
                .apiInfo(metadata());
    }

	private Predicate<String> postPaths() 
	{
		//return or(regex("/smp.*"), regex("/api/javainuse.*"));
		return regex("/msg.*");
	}

	private ApiInfo metadata() 
	{
		return new ApiInfoBuilder().title("Staging-Messaging-Portal API")
			.description("SMP API Reference for Developers at Revature")
			.contact("stanleym@gmail.com")
			.termsOfServiceUrl("https://revature.com/terms-and-conditions/")
			.license("Copyright 2017 Revature LLC")
			.version("1.0")
			.build();
	}
}
