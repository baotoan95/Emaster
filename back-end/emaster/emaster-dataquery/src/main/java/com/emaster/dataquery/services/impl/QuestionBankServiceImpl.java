package com.emaster.dataquery.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.entities.UserMemory;
import com.emaster.dataquery.services.QuestionBankService;
import com.emaster.dataquery.services.StatementService;
import com.emaster.dataquery.services.UserMemoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionBankServiceImpl implements QuestionBankService {
	@Autowired
	private UserMemoryService userMemoryService;
	@Autowired
	private StatementService statementService;
	
	@Override
	public List<Statement> generateQuestionByCategory(String userId, String categoryId, int numOfQuestions) throws DataQueryException {
		log.info("Start generate question by category id={} with userId={}", categoryId, userId);
		// Get missing statements
		List<UserMemory> inMemoryStatements = userMemoryService.findMissing(userId, categoryId, 10);
		List<Statement> missingStatements = inMemoryStatements.stream().map(userMemory -> {
			return statementService.findOne(userMemory.getStatement().getId());
		}).filter(userMemory -> Objects.nonNull(userMemory)).collect(Collectors.toList());
		// If missing statements not enough for a session, then get new statements
		List<Statement> newStatements = statementService.findByCategory(categoryId, Optional.of(0), Optional.of(numOfQuestions - missingStatements.size())).getContent();
		
		List<Statement> learningNeeded = new ArrayList<>();
		Stream.of(missingStatements, newStatements).forEach(learningNeeded::addAll);
		
		log.info("Finish generate question by category id with {} statements", learningNeeded.size());
		return learningNeeded;
	}

}
