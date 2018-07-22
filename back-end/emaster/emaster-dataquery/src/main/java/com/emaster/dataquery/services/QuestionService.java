package com.emaster.dataquery.services;

import java.util.List;

import com.emaster.common.dto.StatementDto;

public interface QuestionService {
	public List<StatementDto> generateQuestions(String userId, String categoryId, int limitNumOfQuestions);
}
