package com.emaster.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.dal.StatementDAL;
import com.emaster.portal.service.StatementService;

@Service
public class StatementServiceImpl implements StatementService {
	private StatementDAL statementDAL;
	
	@Autowired
	public void setStatementDAL(StatementDAL statementDAL) {
		this.statementDAL = statementDAL;
	}

	@Override
	public StatementDto create(StatementDto statementDto) throws PortalException {
		return statementDAL.create(statementDto);
	}

	@Override
	public PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		return statementDAL.findAll(page, size);
	}

	@Override
	public StatementDto update(StatementDto statementDto) throws PortalException {
		return statementDAL.update(statementDto);
	}

	@Override
	public StatementDto findOne(String id) throws PortalException {
		return statementDAL.findOne(id);
	}

	@Override
	public void delete(String id) throws PortalException {
		statementDAL.delete(id);
	}

}
