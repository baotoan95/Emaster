package com.emaster.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.StatementDto;
import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.PortalException;
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
	
	@Override
	public List<StatementDto> generateQuestionByCategory(String userId, String categoryId, int numOfQuestions) throws PortalException {
		log.info("Start generate question by category id={} with userId={}", categoryId, userId);
		// Get missing statements
		List<UserMemoryDto> inMemoryStatements = userMemoryDAL.findMissing(userId, categoryId, 10, numOfQuestions);
		List<StatementDto> missingStatements = inMemoryStatements.stream().map(userMemory -> {
			try {
				return statementService.findOne(userMemory.getStatement().getId());
			} catch (PortalException e) {
				log.error("{}", e);
				return null;
			}
		}).filter(userMemory -> Objects.nonNull(userMemory)).collect(Collectors.toList());
		
		// If missing statements not enough for a session, then get new statements
		List<StatementDto> newStatements = statementService.findByCategory(categoryId, Optional.of(0), Optional.of(numOfQuestions - missingStatements.size())).getContent();
		
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
		
		log.info("Finish generate question by category id with {} statements", learningNeeded.size());
		return learningNeeded;
	}

}
