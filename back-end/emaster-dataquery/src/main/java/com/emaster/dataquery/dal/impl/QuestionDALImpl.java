package com.emaster.dataquery.dal.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emaster.common.entities.Question;
import com.emaster.dataquery.dal.QuestionDAL;
import com.emaster.dataquery.repositories.QuestionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionDALImpl implements QuestionDAL {
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question create(Question question) {
		log.debug("Start create question");
		Question createdQuestion = questionRepository.save(question);
		log.debug("Finish create question");
		return createdQuestion;
	}

	@Override
	public Page<Question> findAll(Pageable pageable) {
		log.debug("Start findAll (page={},size={})", pageable.getPageNumber(), pageable.getPageSize());
		Page<Question> questionPage = questionRepository.findAll(pageable);
		log.debug("Finish findAll");
		return questionPage;
	}

	@Override
	public Question update(Question question) {
		log.debug("Start update question with id={}", question.getId());
		Question updatedQuestion = questionRepository.save(question);
		log.debug("Finish update question");
		return updatedQuestion;
	}

	@Override
	public Question findOne(ObjectId id) {
		log.debug("Start findOne ({})", id);
		Optional<Question> result = questionRepository.findById(id);
		Question question = result.orElse(null);
		log.debug("Finish findOne");
		return question;
	}

	@Override
	public boolean delete(ObjectId id) {
		log.debug("Start delete question with id={}", id);
		boolean isDeleted = false;
		Optional<Question> question = questionRepository.findById(id);
		if(question.isPresent()) {
			try {
				questionRepository.delete(question.get());
				isDeleted = true;
			} catch (Exception e) {
				log.error("Error: {}", e);
			}
		}
		log.debug("Finish delete question. Successed: {}", isDeleted);
		return isDeleted;
	}

}
