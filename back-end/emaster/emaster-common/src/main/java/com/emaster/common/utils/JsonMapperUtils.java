package com.emaster.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class JsonMapperUtils {
	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new ParameterNamesModule())
		.registerModule(new Jdk8Module())
		.registerModule(new JavaTimeModule());
		return objectMapper;
	}
}
