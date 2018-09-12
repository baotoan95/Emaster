package com.emaster.portal.dal;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.PortalException;

public interface StatementDAL {
	StatementDto create(StatementDto statementDto) throws PortalException;

	PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException;

	StatementDto update(StatementDto statementDto) throws PortalException;

	StatementDto findOne(String id) throws PortalException;

	void delete(String id) throws PortalException;
	
	PageDto<StatementDto> findByCategory(String categoryId, Optional<Integer> page, Optional<Integer> size) throws PortalException;
	
	PageDto<StatementDto> findByCategoryExcepting(String categoryId, int limit, String excepted) throws PortalException;
}
