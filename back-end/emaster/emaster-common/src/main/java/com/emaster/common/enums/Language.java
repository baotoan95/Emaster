package com.emaster.common.enums;

public enum Language {
	ENGLISH(1), VIETNAMESE(2);
	
	private int value;
	
	private Language(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
