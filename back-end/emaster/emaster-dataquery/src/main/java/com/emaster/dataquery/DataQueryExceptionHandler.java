package com.emaster.dataquery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.emaster.common.dto.EmasterException;
import com.emaster.common.exception.DataQueryException;

@RestControllerAdvice
public class DataQueryExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { DataQueryException.class })
	protected ResponseEntity<EmasterException> handleException(DataQueryException ex, WebRequest request) {
		EmasterException exception = EmasterException.builder()
				.status(ex.getStatus())
				.message(ex.getMessage())
				.debugMessage(ex.getDebugMessage())
				.dateTime(ex.getDateTime())
				.build();
		return new ResponseEntity<EmasterException>(exception, exception.getStatus());
	}
	
//	@ExceptionHandler(value = { Exception.class })
//	protected ResponseEntity<EmasterException> handleAllException(Exception ex, WebRequest request) {
//		EmasterException exception = EmasterException.builder()
//				.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.message(ex.getMessage())
//				.debugMessage(ex.getStackTrace().toString())
//				.dateTime(LocalDateTime.now()).build();
//		return new ResponseEntity<EmasterException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
