package com.emaster.dataquery.services;

import java.util.List;

import com.emaster.dataquery.entities.Statement;

public interface QuestionService {
	public List<Statement> generateQuestions(String userId, String categoryId, int limitNumOfQuestions);
}
