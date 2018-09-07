package com.emaster.dataquery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.UserMemory;

public interface UserMemoryService {
	Page<UserMemory> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	UserMemory findByUser(String userId);

	List<UserMemory> findByUserAndCategory(String userId, String categoryId);

	UserMemory findByUserAndStatement(String userId, String statementId);
	
	UserMemory create(String userId, String statementId) throws DataQueryException;
	
	void updateCorrectCount(String userId, String statementId, int correctCount) throws DataQueryException;
	
	List<UserMemory> findMissing(String userId, String categoryId, int pointLimit, int limitResult);
}
