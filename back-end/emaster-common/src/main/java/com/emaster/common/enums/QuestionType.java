package com.emaster.common.enums;

public enum QuestionType {
	FORM(1), TRANSLATE(2), LISTEN(1);
	
	private int value;
	
	private QuestionType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
