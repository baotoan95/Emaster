package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.repositories.StatementRepository;
import com.emaster.dataquery.services.StatementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {
	@Autowired
	private StatementRepository statementRepository;

	@Override
	public Statement create(Statement statement) throws DataQueryException {
		log.debug("Start create");
		if(Objects.nonNull(statement)) {
			statement.setId(null);
			Statement createdStatement = statementRepository.save(statement);
			log.debug("Finish create");
			return createdStatement;
		}
		throw DataQueryException.builder()
		.status(HttpStatus.BAD_REQUEST)
		.message(MessageContant.INVALID_PARAM)
		.dateTime(LocalDateTime.now()).build();
	}

	@Override
	public PageDto<Statement> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll(page={},size={})", pageNum, pageSize);
		if(!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder()
			.message(MessageContant.INVALID_PARAM)
			.status(HttpStatus.BAD_REQUEST)
			.dateTime(LocalDateTime.now()).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		PageDto<Statement> pageDto = new PageDto<Statement>().build(statementRepository.findAll(pageable));
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public Statement update(Statement statement) throws DataQueryException {
		if (Objects.nonNull(statement) && statementRepository.existsById(statement.getId())) {
			log.debug("Start update with id={}", statement.getId());
			Statement updatedStatement = statementRepository.save(statement);
			log.debug("Finish update");
			return updatedStatement;
		}
		throw DataQueryException.builder()
		.message("The given id is not existed")
		.status(HttpStatus.BAD_REQUEST)
		.dateTime(LocalDateTime.now()).build();
	}

	@Override
	public Statement findOne(String id) {
		log.debug("Start findOne ({})", id);
		Optional<Statement> result = statementRepository.findById(id);
		log.debug("Finish findOne");
		return result.orElse(null);
	}

	@Override
	public void delete(String id) {
		log.debug("Start delete with id={}", id);
		statementRepository.deleteById(id);
		log.debug("Finish delete");
	}

	@Override
	public List<Statement> getStatementsForASession(String userId, String categoryId) {
		log.debug("Start getStatementsForASession({}, {})", userId, categoryId);
		List<Statement> statements = statementRepository.findByCreatedByIdAndCategoryId(userId, categoryId);
		log.debug("Finished getStatementsForASession() size: {}", statements.size());
		return statements;
	}

}
