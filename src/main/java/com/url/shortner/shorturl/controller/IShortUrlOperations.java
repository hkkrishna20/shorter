package com.url.shortner.shorturl.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.url.shortner.shorturl.dto.UrlReqDTO;

@RequestMapping("/default")
public interface IShortUrlOperations {

	@RequestMapping(value = "/{shorturl}", method = RequestMethod.GET)
	public ResponseEntity<?> getShortUrlByGetMethodWithRequestParam(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("url") Optional<String> id,
			@PathVariable("shorturl") Optional<String> shorturl , @RequestParam("operation") Optional<String> operation);

	@PostMapping("/{operation}")
	public ResponseEntity<?> getShortUrlByPostMethod(HttpServletRequest request, HttpServletResponse response,@RequestBody UrlReqDTO urldto,
			@PathVariable("operation") String operation);
}