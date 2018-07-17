package com.emaster.common.validator;

public class PaginationValidator {
	public static boolean validate(int pageNum, int pageSize) {
		if (pageNum < 0 || pageSize <= 0) {
			return false;
		}
		return true;
	}
}
