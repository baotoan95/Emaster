package com.emaster.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticUtils {
	private static ObjectMapper objectMapper;
	
	public static void setObjectMapper(ObjectMapper objectMapper) {
		StaticUtils.objectMapper = objectMapper;
	}
	
	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
