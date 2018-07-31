package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.entities.QuestionBank;
import com.emaster.dataquery.repositories.QuestionBankRepository;
import com.emaster.dataquery.services.QuestionBankService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionBankServiceImpl implements QuestionBankService {
	@Autowired
	private QuestionBankRepository questionBankRepository;
	
	@Override
	public Page<QuestionBank> finAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.info("Start findAll(page={}, size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder()
			.status(HttpStatus.BAD_REQUEST)
				.message(MessageContant.INVALID_PARAM)
					.dateTime(LocalDateTime.now()).build();
		}

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<QuestionBank> pageQuestionBanks = questionBankRepository.findAll(pageable);
		log.info("Finish findAll()");
		return pageQuestionBanks;
	}

	@Override
	public QuestionBank findOne(String id) {
		log.info("Start findOne({})", id);
		Optional<QuestionBank> questionBank = questionBankRepository.findById(id);
		if(questionBank.isPresent()) {
			log.info("Finish findOne()");
			return questionBank.get();
		}
		return null;
	}

	@Override
	public QuestionBank create(QuestionBank questionBank) throws DataQueryException {
		if(isValid(questionBank)) {
			log.info("Start create by {} with statement {}", 
					questionBank.getCreatedBy().getEmail(), 
					questionBank.getStatement().getId());
			
			questionBank.setCreatedDate(new Date());
			QuestionBank createdQuestionBank = questionBankRepository.save(questionBank);
			
			log.info("End create");
			return createdQuestionBank;
		} else {
			throw DataQueryException.builder()
			.status(HttpStatus.BAD_REQUEST)
			.message(MessageContant.INVALID_PARAM)
			.dateTime(LocalDateTime.now())
			.build();
		}
	}
	
	private boolean isValid(QuestionBank questionBank) {
		return Objects.nonNull(questionBank) && Objects.nonNull(questionBank.getCategory())
				&& Objects.nonNull(questionBank.getStatement()) 
				&& Objects.nonNull(questionBank.getCreatedBy());
	}

	@Override
	public void delete(String id) {
		log.info("Start delete {}", id);
		questionBankRepository.deleteById(id);
		log.info("Finish delete");
	}

	@Override
	public QuestionBank update(QuestionBank questionBank) throws DataQueryException {
		// TODO: try update without primary key
		if(isValid(questionBank)) {
			log.info("Start update createdBy={} statement={} category={}", 
					questionBank.getCreatedBy(), 
					questionBank.getStatement().getId(), 
					questionBank.getCategory().getId());
			QuestionBank updatedQuestionBank = questionBankRepository.save(questionBank);
			return updatedQuestionBank;
		} else {
			throw DataQueryException.builder()
			.message(MessageContant.INVALID_PARAM)
			.status(HttpStatus.BAD_REQUEST)
			.dateTime(LocalDateTime.now()).build();
		}
	}

}
