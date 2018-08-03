package com.emaster.dataquery.facade.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.exception.PortalException;
import com.emaster.dataquery.facade.StatementFacade;
import com.emaster.dataquery.services.StatementService;

@Component
public class StatementFacadeImpl implements StatementFacade {
	private StatementService statementService;
	private ModelMapper modelMapper;
	
	@Autowired
	public void setStatementService(StatementService statementService) {
		this.statementService = statementService;
	}
	
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public StatementDto create(StatementDto Statement) throws DataQueryException {
		
		return null;
	}

	@Override
	public PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		return null;
	}

	@Override
	public StatementDto update(StatementDto Statement) throws DataQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatementDto findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
