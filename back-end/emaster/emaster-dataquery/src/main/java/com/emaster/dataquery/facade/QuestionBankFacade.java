package com.emaster.dataquery.facade;

import java.util.List;

import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;

public interface QuestionBankFacade {
	List<StatementDto> generateByCategory(String userId, String categoryId, int limitQuestions) throws DataQueryException;
}
