package com.emaster.dataquery.facade.impl;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.QuestionBankDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.QuestionBank;
import com.emaster.dataquery.facade.QuestionBankFacade;
import com.emaster.dataquery.services.QuestionBankService;
import com.google.common.reflect.TypeToken;

@Component
public class QuestionBankFacadeImpl implements QuestionBankFacade {
	private QuestionBankService questionBankService;
	private ModelMapper modelMapper;

	@Autowired
	public void setQuestionBankService(QuestionBankService questionBankService) {
		this.questionBankService = questionBankService;
	}

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@SuppressWarnings("serial")
	@Override
	public PageDto<QuestionBankDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		Type pageDtoType = new TypeToken<PageDto<QuestionBankDto>>() {
		}.getType();
		Page<QuestionBank> pageQuestionBank = questionBankService.findAll(page, size);
		PageDto<QuestionBankDto> pageDto = modelMapper.map(pageQuestionBank, pageDtoType);
		pageDto.setContent(pageQuestionBank.stream().map(questionBank -> {
			return modelMapper.map(questionBank, QuestionBankDto.class);
		}).collect(Collectors.toList()));
		return pageDto;
	}

	@Override
	public QuestionBankDto findOne(String id) {
		QuestionBank questionBank = questionBankService.findOne(id);
		if (Objects.nonNull(questionBank)) {
			return modelMapper.map(questionBank, QuestionBankDto.class);
		}
		return null;
	}

	@Override
	public QuestionBankDto create(QuestionBankDto questionBankDto) throws DataQueryException {
		QuestionBank questionBank = modelMapper.map(questionBankDto, QuestionBank.class);
		return modelMapper.map(questionBankService.create(questionBank), QuestionBankDto.class);
	}

	@Override
	public void delete(String id) {
		questionBankService.delete(id);
	}

	@Override
	public QuestionBankDto update(QuestionBankDto questionBankDto) throws DataQueryException {
		QuestionBank questionBank = modelMapper.map(questionBankDto, QuestionBank.class);
		
		return modelMapper.map(questionBankService.update(questionBank), QuestionBankDto.class);
	}

}
