package com.emaster.dataquery.facade.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.facade.QuestionBankFacade;
import com.emaster.dataquery.services.QuestionBankService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QuestionBankFacadeImpl implements QuestionBankFacade {
	@Autowired
	private QuestionBankService questionBankService;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<StatementDto> generateByCategory(String email, String categoryId, int limitQuestions) throws DataQueryException {
		List<Statement> statements = questionBankService.generateQuestionByCategory(email, categoryId, limitQuestions);
		log.info("Start parsing entity to DTO");
		return statements.stream().map(statement -> {
			List<StatementDto> incorrectStatements = statement.getIncorrectAnswers().stream().map(incorrectAnswer -> {
				return StatementDto.builder()
						.sound(incorrectAnswer.getSound())
						.slowSound(incorrectAnswer.getSlowSound())
						.image(incorrectAnswer.getImage())
						.id(incorrectAnswer.getId())
						.content(incorrectAnswer.getContent()).build();
			}).collect(Collectors.toList());
			
			List<StatementDto> correctStatements = statement.getCorrectAnswers().stream().map(correctAnswer -> {
				return StatementDto.builder()
						.sound(correctAnswer.getSound())
						.slowSound(correctAnswer.getSlowSound())
						.image(correctAnswer.getImage())
						.id(correctAnswer.getId())
						.content(correctAnswer.getContent()).build();
			}).collect(Collectors.toList());
			
			statement.setIncorrectAnswers(Collections.emptyList());
			statement.setCorrectAnswers(Collections.emptyList());
			
			StatementDto statementDto = modelMapper.map(statement, StatementDto.class);
			statementDto.setCorrectAnswers(correctStatements);
			statementDto.setIncorrectAnswers(incorrectStatements);
			
			return statementDto;
		}).collect(Collectors.toList());
	}
}
