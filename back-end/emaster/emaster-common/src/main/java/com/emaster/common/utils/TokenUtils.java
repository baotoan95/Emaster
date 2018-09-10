package com.emaster.common.utils;

import java.io.IOException;
import java.util.Objects;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenUtils {
	private static ObjectMapper objectMapper;
	
	private TokenUtils() {
		
	}
	
	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		TokenUtils.objectMapper = objectMapper;
	}
	
	public static TokenDto parseToken(String token) {
		if (Objects.nonNull(token)) {
			try {
				String[] splittedToken = token.split("\\.");
				String tokenBody = splittedToken[1];

				Base64 base64 = new Base64(true);
				String jsonToken = new String(base64.decode(tokenBody));
				return objectMapper.readValue(jsonToken, TokenDto.class);
			} catch (IOException e) {
				log.error("{}", e);
			}
		}
		return null;
	}
}
