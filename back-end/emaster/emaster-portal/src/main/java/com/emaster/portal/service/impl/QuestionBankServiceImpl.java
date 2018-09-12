package com.emaster.portal.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageConstant;
import com.emaster.common.dto.AnswerDto;
import com.emaster.common.dto.QuestionDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.config.UserSession;
import com.emaster.portal.dal.UserMemoryDAL;
import com.emaster.portal.service.QuestionBankService;
import com.emaster.portal.service.StatementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionBankServiceImpl implements QuestionBankService {
	@Autowired
	private UserMemoryDAL userMemoryDAL;
	@Autowired
	private StatementService statementService;
	@Autowired
	private UserSession userSession;
	@Autowired
	private ModelMapper modelMapper;
	
	private static final int NUMBER_OF_QUESTION = 20;
	
	@Override
	public List<QuestionDto> generateQuestionByCategory(String categoryId) throws PortalException {
		String userId = userSession.getUserId();
		if(Objects.isNull(userId)) {
			throw PortalException.builder()
			.status(HttpStatus.UNAUTHORIZED)
			.dateTime(LocalDateTime.now())
					.message(MessageConstant.NOT_HAVE_PERMISSION).build();
		}
		log.info("Start generate question by category id={} with userId={}", categoryId, userId);
		// Get missing statements
		List<UserMemoryDto> inMemoryStatements = userMemoryDAL.findMissing(userId, categoryId, 10);
		List<StatementDto> missingStatements = inMemoryStatements.stream().map(userMemory -> {
			try {
				return statementService.findOne(userMemory.getStatement().getId());
			} catch (PortalException e) {
				log.error("{}", e);
				return null;
			}
		}).filter(userMemory -> Objects.nonNull(userMemory)).collect(Collectors.toList());
		
		// If missing statements not enough for a session, then get new statements
		List<String> ids = missingStatements.stream().map(statement -> {
			return statement.getId();
		}).collect(Collectors.toList());
		String excepted = StringUtils.join(ids, ",");
		List<StatementDto> newStatements = statementService.findByCategoryExcepting(categoryId, NUMBER_OF_QUESTION - missingStatements.size(), excepted).getContent();
		
		// Add new statements to memory
		newStatements.stream().forEach(newStatement -> {
			try {
				userMemoryDAL.addToMemory(userId, newStatement.getId());
			} catch (PortalException e) {
				log.error("{}", e);
			}
		});
		
		List<StatementDto> learningNeeded = new ArrayList<>();
		Stream.of(missingStatements, newStatements).forEach(learningNeeded::addAll);
		
		// Shuffle questions
		Collections.shuffle(learningNeeded);
		
		log.info("Finish generate question by category id with {} statements", learningNeeded.size());
		return convertToQuestionDtos(learningNeeded);
	}
	
	private List<QuestionDto> convertToQuestionDtos(List<StatementDto> statements) {
		return statements.stream().map(statement -> {
			return convertToQuestionDto(statement);
		}).collect(Collectors.toList());
	}
	
	private QuestionDto convertToQuestionDto(StatementDto statement) {
		// Join correct answers and incorrect answers
		List<AnswerDto> answers = Stream.of(
				statement.getCorrectAnswers().stream().map(answer -> {
					AnswerDto answerDto = modelMapper.map(answer, AnswerDto.class);
					answerDto.setCorrect(true);
					return answerDto;
				}).collect(Collectors.toList()), 
				statement.getIncorrectAnswers().stream().map(answer -> {
					AnswerDto answerDto = modelMapper.map(answer, AnswerDto.class);
					answerDto.setCorrect(false);
					return answerDto;
				}).collect(Collectors.toList())).flatMap(Collection::stream).collect(Collectors.toList());
		
		// Shuffle list answers
		Collections.shuffle(answers);
		
		// Remove correct answers and incorrect answers for convert statement to question DTO
		statement.setCorrectAnswers(null);
		statement.setIncorrectAnswers(null);
		
		// Convert statement to question DTO
		QuestionDto questionDto = modelMapper.map(statement, QuestionDto.class);
		questionDto.setAnswers(answers);
		
		// TODO random question type
		
		return questionDto;
	}
	
}
