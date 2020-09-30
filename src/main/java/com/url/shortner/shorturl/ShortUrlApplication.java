package com.url.shortner.shorturl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.shorturl.config.RequestObjectFilter;
import com.url.shortner.shorturl.controller.exceptions.Handler;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan
public class ShortUrlApplication {

	public static void main(String[] args) {
		Handler globalExceptionHandler = new Handler();
		Thread.setDefaultUncaughtExceptionHandler(globalExceptionHandler);
		SpringApplication.run(ShortUrlApplication.class, args);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(basicAuthScheme()).forPaths(PathSelectors.ant("ph/v1/**"))
				.build();
	}

	private List<SecurityReference> basicAuthScheme() {
		final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { authorizationScope };
		return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));

	}

	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}

	@Bean
	public Docket apiDocket() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				// .apis(RequestHandlerSelectors.basePackage("com.url.shortner.shorturl"))
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()));
		return docket;
	}

	@Bean
	public FilterRegistrationBean ResistFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new RequestObjectFilter());
		registration.setName("customFilter");
		registration.addUrlPatterns("/gt/*");
		registration.setOrder(10);
		return registration;
	}

}
