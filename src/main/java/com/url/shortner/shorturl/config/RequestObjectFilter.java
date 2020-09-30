package com.url.shortner.shorturl.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Order(1)
public class RequestObjectFilter extends OncePerRequestFilter implements HandlerInterceptor  {
	private static final Logger log = LoggerFactory.getLogger(RequestObjectFilter.class);
	static final String HEADER_STRING = "Authorization";

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside doFilterInternal-------------------------------------------------");
		String requestUrl = request.getRequestURL().toString().toLowerCase();
		System.out.println("requestUrl" + requestUrl);
		String remote_addr = request.getRemoteAddr();
		System.out.println("Remote Address" + remote_addr);
		filterChain.doFilter(request, response);

	}

	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getName();
				}
			}
		}
		return null;
	}

	@Override
	public void destroy() {
		log.info("filter Destruction");
	}
}