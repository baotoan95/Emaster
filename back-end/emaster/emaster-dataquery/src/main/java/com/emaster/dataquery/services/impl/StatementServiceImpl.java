package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.repositories.StatementRepository;
import com.emaster.dataquery.repositories.UserRepository;
import com.emaster.dataquery.services.StatementService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {
	@Autowired
	private StatementRepository statementRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Statement create(Statement statement) throws DataQueryException {
		log.info("Start create");
		if (Objects.nonNull(statement)) {
			if(Objects.isNull(statement.getCreatedBy())) {
				statement.setCreatedBy(User.builder().email(Strings.EMPTY).build());
			}
			Optional<User> user = userRepository.findByEmail(statement.getCreatedBy().getEmail());
			if (user.isPresent()) {
				statement.setId(null);
				statement.setCreatedDate(new Date());
				statement.setCreatedBy(user.get());
				Statement createdStatement = statementRepository.save(statement);
				log.info("Finish create");
				return createdStatement;
			} else {
				throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST)
						.message(DataQueryMessage.DONT_HAVE_PERMIT_CREATE).dateTime(LocalDateTime.now()).build();
			}
		}
		throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).message(MessageContant.INVALID_PARAM)
				.dateTime(LocalDateTime.now()).build();
	}

	@Override
	public Page<Statement> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.info("Start findAll(page={},size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().message(MessageContant.INVALID_PARAM).status(HttpStatus.BAD_REQUEST)
					.dateTime(LocalDateTime.now()).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Statement> pageStatement = statementRepository.findAllByOrderByCreatedDateDesc(pageable);
		log.info("Finish findAll");
		return pageStatement;
	}

	@Override
	public Statement update(Statement statement) throws DataQueryException {
		Optional<Statement> existedStatement = statementRepository.findById(statement.getId());
		if (existedStatement.isPresent()) {
			log.info("Start update with id={}", statement.getId());
			// Remove itself from incorrect and correct list
			statement.setCorrectAnswers(statement.getCorrectAnswers().stream().filter(stt -> !stt.getId().equals(statement.getId())).collect(Collectors.toList()));
			statement.setIncorrectAnswers(statement.getIncorrectAnswers().stream().filter(stt -> !stt.getId().equals(statement.getId())).collect(Collectors.toList()));
			
			Statement oldStatement = existedStatement.get();
			statement.setUpdatedDate(new Date());
			statement.setCreatedDate(oldStatement.getCreatedDate());
			statement.setCreatedBy(oldStatement.getCreatedBy());
			Statement updatedStatement = statementRepository.save(statement);
			log.info("Finish update");
			return updatedStatement;
		} else {
			throw DataQueryException.builder().message("The given id is not existed").status(HttpStatus.BAD_REQUEST)
				.dateTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public Statement findOne(String id) {
		log.info("Start findOne ({})", id);
		Optional<Statement> result = statementRepository.findById(id);
		if (result.isPresent()) {
			log.info("Finish findOne");
			return result.get();
		}
		return null;
	}

	@Override
	public void delete(String id) {
		log.info("Start delete with id={}", id);
		statementRepository.deleteById(id);
		log.info("Finish delete");
	}

	@Override
	public List<Statement> getStatementsForASession(String email, String categoryId) {
		log.info("Start getStatementsForASession({}, {})", email, categoryId);
		List<Statement> statements = statementRepository.findByCreatedByEmailAndCategoryId(email, categoryId);
		log.info("Finished getStatementsForASession() size: {}", statements.size());
		return statements;
	}

}
