package com.emaster.common.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Language {
	ENGLISH(1), VIETNAMESE(2);
	
	private int value;
	
	private Language(int value) {
		this.value = value;
	}
	
	@JsonCreator
	public static Language get(String name) {
		return Arrays.stream(Language.values())
                .filter(z -> z.name().equals(name))
                .findFirst().orElse(null);
	}
	
	public int getValue() {
		return this.value;
	}
}
