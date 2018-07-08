package com.emaster.dataquery.dal;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emaster.common.entities.Question;

public interface QuestionDAL {
	Question create(Question question);
	
	Page<Question> findAll(Pageable pageable);
	
	Question update(Question question);
	
	Question findOne(ObjectId id);
	
	boolean delete(ObjectId id);
}
