package com.emaster.dataquery.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emaster.dataquery.entities.Question;
import com.emaster.dataquery.exception.NotFoundException;

public interface QuestionService {
	Question create(Question question);

	Page<Question> findAll(Pageable pageable);

	Question update(Question question) throws NotFoundException;

	Question findOne(String id);

	void delete(String id);
}
