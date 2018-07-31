package com.emaster.portal.dal;

import java.util.List;

import com.emaster.common.dto.StatementDto;

public interface QuestionDAL {
	public List<StatementDto> generateQuestions(String userId, String categoryId, int limitNumOfQuestions);
}
