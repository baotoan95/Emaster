package com.emaster.dataquery.services;

import java.util.List;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;

public interface QuestionBankService {
	public List<Statement> generateQuestionByCategory(String email, String categoryId, int numOfQuestions) throws DataQueryException;
}
