package com.emaster.portal.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emaster.common.dto.EmasterException;
import com.emaster.common.exception.PortalException;

@RestControllerAdvice
public class PortalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { PortalException.class })
	protected ResponseEntity<EmasterException> handleException(PortalException ex, WebRequest request) {
		EmasterException exception = EmasterException.builder().status(ex.getStatus()).message(ex.getMessage())
				.debugMessage(ex.getDebugMessage()).dateTime(ex.getDateTime()).build();
		return new ResponseEntity<EmasterException>(exception, exception.getStatus());
	}

	// @ExceptionHandler(value = { Exception.class })
	// protected ResponseEntity<EmasterException> handleAllException(Exception ex,
	// WebRequest request) {
	// EmasterException exception =
	// EmasterException.builder().status(HttpStatus.INTERNAL_SERVER_ERROR)
	// .message(ex.getMessage()).debugMessage(ex.getMessage()).dateTime(LocalDateTime.now()).build();
	// return new ResponseEntity<EmasterException>(exception,
	// HttpStatus.INTERNAL_SERVER_ERROR);
	// }

}
