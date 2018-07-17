package com.emaster.common.enums;

public enum StatementType {
	WRONG(1), TEXT(2), GAP(3);
	
	private int value;
	
	private StatementType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
