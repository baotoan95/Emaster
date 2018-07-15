package com.emaster.common.dto;

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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dateTime;
	private String message;
	private String debugMessage;
	
	@JsonCreator
	public static EmasterException Create(String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = JsonMapperUtils.getObjectMapper();
		return mapper.readValue(jsonString, EmasterException.class);
	}
}
