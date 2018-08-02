package com.emaster.dataquery.facade;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.QuestionBankDto;
import com.emaster.common.exception.DataQueryException;

public interface QuestionBankFacade {
	public PageDto<QuestionBankDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	public QuestionBankDto findOne(String id);

	public QuestionBankDto create(QuestionBankDto questionBankDto) throws DataQueryException;

	public void delete(String id);

	public QuestionBankDto update(QuestionBankDto questionBankDto) throws DataQueryException; 
}
