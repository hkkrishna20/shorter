package com.url.shortner.shorturl.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.shorturl.dto.UrlReqDTO;
import com.url.shortner.shorturl.service.ShortUrlService;

/**
 * @author HDMI
 *
 */
@RestController
@RequestMapping("/api")
public class ShortUrlController implements IShortUrlOperations {
	@Autowired
	private ShortUrlService shortUrlService;

	@Override
	public ResponseEntity<?> getShortUrlByGetMethodWithRequestParam(HttpServletRequest request,
			HttpServletResponse response, Optional<String> url, Optional<String> shorturl, Optional<String> operation) {
		// TODO Auto-generated method stub
		String shurl = "";
		String urlFull = "";
		String op = "";
		if (shorturl.isPresent()) {
			shurl = shorturl.get();
		}
		if (url.isPresent()) {
			urlFull = url.get();
		}
		if (operation.isPresent()) {
			op = operation.get();
		}
		return shortUrlService.operation(request, response, urlFull, op, shurl);
	}

	@Override
	public ResponseEntity<?> getShortUrlByPostMethod(HttpServletRequest request, HttpServletResponse response,
			UrlReqDTO urldto, String operation) {
		// TODO Auto-generated method stub
		return shortUrlService.operation(request, response, urldto.getUrl(), "generate", "");
	}

}
