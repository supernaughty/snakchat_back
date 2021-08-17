package com.snackchat.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.snackchat.exception.NoSearchObjectException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionController {

	// 400
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> BadRequestException(final Exception ex) {
		log.warn("error", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	// 404
	@ExceptionHandler({ NoSearchObjectException.class })
	public ResponseEntity<Object> notFoundException(final NoSearchObjectException ex) {
		log.warn("error", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	// 500
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(final Exception ex) {
		log.info(ex.getClass().getName());
		log.error("error", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
