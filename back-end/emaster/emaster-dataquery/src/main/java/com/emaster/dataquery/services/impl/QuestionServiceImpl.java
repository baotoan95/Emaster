package com.emaster.dataquery.services.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emaster.dataquery.entities.Question;
import com.emaster.dataquery.exception.NotFoundException;
import com.emaster.dataquery.repositories.QuestionRepository;
import com.emaster.dataquery.services.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question create(Question question) {
		log.debug("Start create");
		Question createdQuestion = questionRepository.save(question);
		log.debug("Finish create");
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
	public Question update(Question question) throws NotFoundException {
		log.debug("Start update with id={}", question.getId());
		if (questionRepository.existsById(question.getId())) {
			Question updatedQuestion = questionRepository.save(question);
			log.debug("Finish update");
			return updatedQuestion;
		} else {
			throw new NotFoundException("The given id is not existed");
		}
	}

	@Override
	public Question findOne(ObjectId id) {
		log.debug("Start findOne ({})", id);
		Optional<Question> result = questionRepository.findById(id);
		log.debug("Finish findOne");
		return result.orElse(null);
	}

	@Override
	public void delete(ObjectId id) {
		log.debug("Start delete with id={}", id);
		questionRepository.deleteById(id);
		log.debug("Finish delete");
	}

}
