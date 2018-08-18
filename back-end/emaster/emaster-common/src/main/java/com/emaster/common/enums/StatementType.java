package com.emaster.common.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatementType {
	WRONG(1), TEXT(2), GAP(3);
	
	private int value;
	
	private StatementType(int value) {
		this.value = value;
	}
	
	@JsonCreator
	public static StatementType get(String name) {
		return Arrays.stream(StatementType.values())
                .filter(z -> z.name().equals(name))
                .findFirst().orElse(null);
	}
	
	public int getValue() {
		return this.value;
	}
}
