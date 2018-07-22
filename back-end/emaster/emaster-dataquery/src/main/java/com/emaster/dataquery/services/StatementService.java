package com.emaster.dataquery.services;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;

public interface StatementService {
	StatementDto create(StatementDto Statement) throws DataQueryException;

	PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	StatementDto update(StatementDto Statement) throws DataQueryException;

	StatementDto findOne(String id);

	void delete(String id);

	List<StatementDto> getStatementsForASession(String userId, String categoryId);
}
