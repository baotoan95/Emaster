package com.emaster.common.exception;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.emaster.common.parser.JsonDateDeserializer;
import com.emaster.common.parser.JsonDateSerializer;
import com.emaster.common.utils.JsonMapperUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmasterException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8531839552937130341L;
	private HttpStatus status;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dateTime;
	private String message;
	private String debugMessage;
	
	@JsonCreator
	public static EmasterException create(String jsonString)
			throws IOException {
		ObjectMapper mapper = JsonMapperUtils.getObjectMapper();
		return mapper.readValue(jsonString, EmasterException.class);
	}
}
