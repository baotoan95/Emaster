package com.emaster.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8718548770570701086L;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dateTime;
	private String message;
	private String debugMessage;
	
	public PortalException(String message) {
		this.message = message;
		this.dateTime = LocalDateTime.now();
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public PortalException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.status = httpStatus;
		this.dateTime = LocalDateTime.now();
	}
	
	public PortalException(HttpStatus httpStatus) {
		this.status = httpStatus;
		this.dateTime = LocalDateTime.now();
	}
	
	
	
}
