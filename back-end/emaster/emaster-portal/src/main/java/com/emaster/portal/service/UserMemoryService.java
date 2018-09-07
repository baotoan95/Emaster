package com.emaster.portal.service;

import java.util.List;

import com.emaster.common.dto.UserMemoryDto;

public interface UserMemoryService {
	List<UserMemoryDto> findMissing(String userId, String categoryId, int pointLimit, int limitResult);
}
