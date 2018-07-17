package com.emaster.dataquery.services;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.UserMemory;

public interface UserMemoryService {
	PageDto<UserMemory> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	UserMemory findByUser(String userId);

	List<UserMemory> findByUserAndCategory(String userId, String categoryId);

	UserMemory findByUserAndStatement(String userId, String statementId);
	
	UserMemory create(UserMemory userMemory) throws DataQueryException;
	
	void updateCorrectCount(String userId, String statementId, int correctCount) throws DataQueryException;
}
