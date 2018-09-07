package com.emaster.dataquery.facade;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.DataQueryException;

public interface UserMemoryFacade {
	PageDto<UserMemoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	UserMemoryDto findByUser(String userId);

	List<UserMemoryDto> findByUserAndCategory(String userId, String categoryId);

	UserMemoryDto findByUserAndStatement(String userId, String statementId);
	
	UserMemoryDto create(String userId, String statementId) throws DataQueryException;
	
	void updateCorrectCount(String userId, String statementId, int correctCount) throws DataQueryException;
	
	List<UserMemoryDto> findMissing(String userId, String categoryId, int pointLimit, int limitResult);
}
