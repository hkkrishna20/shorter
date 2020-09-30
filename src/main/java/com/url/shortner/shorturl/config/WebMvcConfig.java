package com.url.shortner.shorturl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestObjectFilter()).addPathPatterns("/**")
				.excludePathPatterns("/static/**", "/assets/**", "/css/**", "/images/**", "/layui_ext/**", "/login",
						"/login/main", "/genCaptcha", "swagger-resources/**", "v2/api-docs/**", "/csrf/**")
				.excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
		;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		// super.addResourceHandlers(registry);
		registry.addResourceHandler("/swagger-resources/**").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/v2/**");
		registry.addResourceHandler("/csrf/**");

	}
}
