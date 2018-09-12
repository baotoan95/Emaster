package com.emaster.portal.service;

import java.util.List;

import com.emaster.common.dto.QuestionDto;
import com.emaster.common.exception.PortalException;

public interface QuestionBankService {
	public List<QuestionDto> generateQuestionByCategory(String categoryId) throws PortalException;
}
