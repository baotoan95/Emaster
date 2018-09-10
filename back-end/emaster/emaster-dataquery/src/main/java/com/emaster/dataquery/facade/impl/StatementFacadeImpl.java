package com.emaster.dataquery.facade.impl;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;
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
	public StatementDto create(StatementDto statementDto) throws DataQueryException {
		Statement statement = modelMapper.map(statementDto, Statement.class);
		statement.setCorrectAnswers(statementDto.getCorrectAnswers().stream().map(answer -> {
			return Statement.builder().id(answer.getId()).build();
		}).collect(Collectors.toList()));
		statement.setIncorrectAnswers(statementDto.getIncorrectAnswers().stream().map(answer -> {
			return Statement.builder().id(answer.getId()).build();
		}).collect(Collectors.toList()));

		statement = statementService.create(statement);
		StatementDto newStatementDto = modelMapper.map(statement, StatementDto.class);
		newStatementDto.setCorrectAnswers(statement.getCorrectAnswers().stream().map(answer -> {
			return StatementDto.builder().id(answer.getId()).content(answer.getContent()).build();
		}).collect(Collectors.toList()));
		newStatementDto.setIncorrectAnswers(statement.getIncorrectAnswers().stream().map(answer -> {
			return StatementDto.builder().id(answer.getId()).content(answer.getContent()).build();
		}).collect(Collectors.toList()));

		return newStatementDto;
	}

	@Override
	public PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		Page<Statement> pageStatement = statementService.findAll(page, size);
		Type pageStatementDtoType = new TypeToken<PageDto<StatementDto>>() {
		}.getType();
		PageDto<StatementDto> pageStatementDto = modelMapper.map(pageStatement, pageStatementDtoType);
		pageStatementDto.setContent(pageStatement.getContent().stream().map(statement -> {
			statement.setIncorrectAnswers(null);
			statement.setCorrectAnswers(null);
			return modelMapper.map(statement, StatementDto.class);
		}).collect(Collectors.toList()));
		return pageStatementDto;
	}

	@Override
	public StatementDto update(StatementDto statementDto) throws DataQueryException {
		Statement statement = modelMapper.map(statementDto, Statement.class);
		// Prevent null for collection
		statementDto
				.setCorrectAnswers(Objects.nonNull(statementDto.getCorrectAnswers()) ? statementDto.getCorrectAnswers()
						: Collections.emptyList());
		statementDto.setIncorrectAnswers(
				Objects.nonNull(statementDto.getIncorrectAnswers()) ? statementDto.getIncorrectAnswers()
						: Collections.emptyList());

		statement.setCorrectAnswers(statementDto.getCorrectAnswers().stream().map(answer -> {
			return Statement.builder().id(answer.getId()).build();
		}).collect(Collectors.toList()));
		statement.setIncorrectAnswers(statementDto.getIncorrectAnswers().stream().map(answer -> {
			return Statement.builder().id(answer.getId()).build();
		}).collect(Collectors.toList()));

		statement = statementService.update(statement);
		StatementDto updatedStatementDto = modelMapper.map(statement, StatementDto.class);
		updatedStatementDto.setCorrectAnswers(statement.getCorrectAnswers().stream().map(answer -> {
			return StatementDto.builder().id(answer.getId()).content(answer.getContent()).build();
		}).collect(Collectors.toList()));
		updatedStatementDto.setIncorrectAnswers(statement.getIncorrectAnswers().stream().map(answer -> {
			return StatementDto.builder().id(answer.getId()).content(answer.getContent()).build();
		}).collect(Collectors.toList()));

		return updatedStatementDto;
	}

	@Override
	public StatementDto findOne(String id) {
		Statement statement = statementService.findOne(id);
		if (Objects.nonNull(statement)) {
			statement.setCorrectAnswers(
					statement.getCorrectAnswers().stream().filter(answer -> Objects.nonNull(answer)).map(answer -> {
						answer.setCorrectAnswers(null);
						answer.setIncorrectAnswers(null);
						return answer;
					}).collect(Collectors.toList()));
			statement.setIncorrectAnswers(
					statement.getIncorrectAnswers().stream().filter(answer -> Objects.nonNull(answer)).map(answer -> {
						answer.setCorrectAnswers(null);
						answer.setIncorrectAnswers(null);
						return answer;
					}).collect(Collectors.toList()));
			return modelMapper.map(statement, StatementDto.class);
		}
		return null;
	}

	@Override
	public void delete(String id) {
		statementService.delete(id);
	}

	@Override
	public List<StatementDto> search(String content) {
		List<Statement> searchResult = statementService.searchByContent(content);
		return searchResult.stream().map(statement -> {
			statement.setIncorrectAnswers(null);
			statement.setCorrectAnswers(null);
			return modelMapper.map(statement, StatementDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public PageDto<StatementDto> findByCategory(String categoryId, Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		Page<Statement> pageStatement = statementService.findByCategory(categoryId, page, size);
		Type pageStatementDtoType = new TypeToken<PageDto<StatementDto>>() {
		}.getType();
		PageDto<StatementDto> pageStatementDto = modelMapper.map(pageStatement, pageStatementDtoType);
		pageStatementDto.setContent(pageStatement.getContent().stream().map(statement -> {
			statement.setIncorrectAnswers(null);
			statement.setCorrectAnswers(null);
			return modelMapper.map(statement, StatementDto.class);
		}).collect(Collectors.toList()));
		return pageStatementDto;
	}

}
