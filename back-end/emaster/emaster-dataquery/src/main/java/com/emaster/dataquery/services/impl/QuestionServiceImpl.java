package com.emaster.dataquery.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.services.QuestionService;
import com.emaster.dataquery.services.StatementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private StatementService statementService;
	
	@Override
	public List<Statement> generateQuestions(String userId, String categoryId, int limitNumOfQuestions) {
		return statementService.getStatementsForASession(userId, categoryId);
	}

}
