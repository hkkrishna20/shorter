package com.url.shortner.shorturl.service;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.url.shortner.shorturl.dto.UrlResponseDTO;
import com.url.shortner.shorturl.model.UrlShortEvent;
import com.url.shortner.shorturl.repo.IUrlShortEventDao;

@Service
public class ShortUrlService {
	private static long previousTimeMillis = System.currentTimeMillis();
	private static long counter = 0L;
	@Autowired
	ConvertorUrlFactory convUrlFactory;

	@Autowired
	IUrlShortEventDao urlSaveRepo;

	public static synchronized long nextID() {
		long currentTimeMillis = System.currentTimeMillis();
		counter = (currentTimeMillis == previousTimeMillis) ? (counter + 1L) & 1048575L : 0L;
		previousTimeMillis = currentTimeMillis;
		long timeComponent = (currentTimeMillis & 8796093022207L) << 20;
		return timeComponent | counter;
	}

	public ResponseEntity<?> operation(HttpServletRequest request, HttpServletResponse response, String url,
			String operation, String shorturl) {
		// TODO Auto-generated method stub
		if (operation.equalsIgnoreCase("generate")) {
			UrlShortEvent entity = genShortUrl(url);
			UrlResponseDTO responseDto = new UrlResponseDTO();
			responseDto.setExpiresin(entity.getExpiryDate());
			responseDto.setShorturl(entity.getShorturl());
			responseDto.setUrl(url);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);

		} else  {
			/*
			 * Decoder decoder = Base64.getUrlDecoder(); byte[] bytes =
			 * decoder.decode(encodedUrl);
			 * 
			 * System.out.println(new String(bytes));
			 */
//			HttpHeaders headers = new HttpHeaders();
//			headers.setLocation(URI.create());
//			return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
			try {
				return ResponseEntity.status(HttpStatus.FOUND)
		                .location(URI.create(redirectOriginalUrl(shorturl)))
		                .build();
//				response.sendRedirect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

	}

	public UrlShortEvent genShortUrl(String urlString) {
		long days = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		Date expiry = new Date(System.currentTimeMillis() + days);
		UrlShortEvent url = new UrlShortEvent();
		url.setExpiryDate(expiry);
		url.setUrlText(urlString);
		long nextId = nextID();
		url.setUrlId(nextId);
		UrlShortEvent entity = urlSaveRepo.save(url);
		String shrtUrl = convUrlFactory.encode(entity.getUrlId());
		entity.setShorturl(shrtUrl);
		entity = urlSaveRepo.save(entity);
		return entity;
	}

	public String redirectOriginalUrl(String shortUrl) {
		// long id = convUrlFactory.decode(shortUrl);
		// System.out.println("Id : " + id);
		UrlShortEvent entity = urlSaveRepo.findByShorturl(shortUrl);

		if (entity == null) {
			new EntityNotFoundException("There is no entity with " + shortUrl);
		}
//				.orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));
		if (entity.getExpiryDate() != null && entity.getExpiryDate().before(new Date())) {
			urlSaveRepo.delete(entity);
			throw new EntityNotFoundException("Link expired!");
		}
		return entity.getUrlText();
	}
}
