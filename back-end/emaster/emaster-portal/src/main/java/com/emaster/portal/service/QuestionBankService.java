package com.emaster.portal.service;

import java.util.List;

import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.PortalException;

public interface QuestionBankService {
	public List<StatementDto> generateQuestionByCategory(String categoryId) throws PortalException;
}
