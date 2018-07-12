package com.emaster.portal.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emaster.common.exception.ApiException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiException apiException = ApiException.builder().message(ex.getMessage())
				.debugMessage(ex.getLocalizedMessage()).build();
		return buildResponseEntity(apiException);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiException apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
