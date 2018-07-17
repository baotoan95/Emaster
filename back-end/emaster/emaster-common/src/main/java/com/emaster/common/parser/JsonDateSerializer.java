package com.emaster.common.parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<LocalDateTime> {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	@Override
	public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		final String dateString = date.format(this.formatter);
	    generator.writeString(dateString);
	}

}
