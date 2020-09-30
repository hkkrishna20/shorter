package com.url.shortner.shorturl.controller.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.url.shortner.shorturl.common.CustomErrorResponse;
import com.url.shortner.shorturl.common.NotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
	private static final Logger log = LogManager.getLogger(ExceptionAdvice.class);

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<?> handleRunTimeException(RuntimeException e) {
		return error(INTERNAL_SERVER_ERROR, e);
	}

	private ResponseEntity<?> error(HttpStatus status, Exception e) {
		log.error("Exception : ", e);
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((status.value()));
		return new ResponseEntity<>(error, status);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(NotFoundException e) {
		CustomErrorResponse error = new CustomErrorResponse("NOT_FOUND_ERROR", e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus((HttpStatus.NOT_FOUND.value()));
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}