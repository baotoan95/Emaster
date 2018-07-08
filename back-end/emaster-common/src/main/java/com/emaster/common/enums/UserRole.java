package com.emaster.common.enums;

public enum UserRole {
	USER(1), MOD(2), ADMIN(1);
	
	private int value;
	
	private UserRole(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
}
