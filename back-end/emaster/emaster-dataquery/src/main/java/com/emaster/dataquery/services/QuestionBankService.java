package com.emaster.dataquery.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.QuestionBank;

public interface QuestionBankService {
	public Page<QuestionBank> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	public QuestionBank findOne(String id);

	public QuestionBank create(QuestionBank questionBank) throws DataQueryException;

	public void delete(String id);

	public QuestionBank update(QuestionBank questionBank) throws DataQueryException;
}
