package com.emaster.dataquery.facade;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;

public interface StatementFacade {
	StatementDto create(StatementDto statementDto) throws DataQueryException;

	PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	StatementDto update(StatementDto statementDto) throws DataQueryException;

	StatementDto findOne(String id);

	void delete(String id);
	
	List<StatementDto> search(String content);
	
	public PageDto<StatementDto> findByCategory(String categoryId, Optional<Integer> page, Optional<Integer> size) throws DataQueryException;
}
