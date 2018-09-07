package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.constant.Point;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.entities.UserMemory;
import com.emaster.dataquery.repositories.StatementRepository;
import com.emaster.dataquery.repositories.UserMemoryRepository;
import com.emaster.dataquery.services.UserMemoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserMemorySerivceImpl implements UserMemoryService {
	@Autowired
	private UserMemoryRepository userMemoryRepository;
	@Autowired
	private StatementRepository statementRepository;

	@Override
	public Page<UserMemory> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.info("Start findAll({}, {})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).dateTime(LocalDateTime.now())
					.message(MessageContant.INVALID_PARAM).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<UserMemory> pageUserMemory = userMemoryRepository.findAll(pageable);
		log.info("Finish findAll");
		return pageUserMemory;
	}

	@Override
	public UserMemory findByUser(String email) {
		log.info("Start findByUser({})", email);
		Optional<UserMemory> userMemory = userMemoryRepository.findByUserEmail(email);
		log.info("Finish findByUser");
		return userMemory.orElse(null);
	}

	@Override
	public List<UserMemory> findByUserAndCategory(String email, String categoryId) {
		log.info("Start findByUserAndCategory({}, {})", email, categoryId);
		List<UserMemory> users = userMemoryRepository.findByUserEmailAndStatementCategoryId(email, categoryId);
		log.info("Finish findByUserAndCategory");
		return users;
	}

	@Override
	public UserMemory findByUserAndStatement(String email, String statementId) {
		log.info("Start findByUserAndStatement({}, {})", email, statementId);
		Optional<UserMemory> userMemory = userMemoryRepository.findByUserEmailAndStatementId(email, statementId);
		log.info("Finish findByUserAndStatement");
		return userMemory.orElse(null);
	}

	@Override
	public UserMemory create(String userId, String statementId) throws DataQueryException {
		if (Objects.isNull(userId) || StringUtils.isEmpty(statementId)) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST)
					.message("Statement ID and User ID are required").dateTime(LocalDateTime.now()).build();
		}
		log.info("Start create with statementId = {}, userId = {}", statementId,
				userId);
		Optional<Statement> statement = statementRepository.findById(statementId);
		if(statement.isPresent()) {
			UserMemory createdUserMemory = userMemoryRepository.save(UserMemory.builder().correctCount(0)
					.incorrectCount(0)
					.point(0).startLearnDate(new Date()).lastLearnTime(new Date())
					.statement(statement.get()).build());
			log.info("Finish create");
			return createdUserMemory;
		} else {
			throw DataQueryException.builder()
			.status(HttpStatus.BAD_REQUEST)
			.message("Can't found statement")
			.dateTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public void updateCorrectCount(String userId, String statementId, int correctCount) throws DataQueryException {
		log.info("Start updateCorrectCount({}, {}, {})", userId, statementId, correctCount);
		UserMemory userMemory = findByUserAndStatement(userId, statementId);
		if (Objects.nonNull(userMemory)) {
			userMemory.setCorrectCount(userMemory.getCorrectCount() + correctCount);
			userMemory.setIncorrectCount(
					correctCount < 0 ? userMemory.getIncorrectCount() - correctCount : userMemory.getIncorrectCount());
			userMemory.setPoint(userMemory.getPoint() + (correctCount * Point.POINT_PER_QUESTION));
			UserMemory updatedUserMemory = userMemoryRepository.save(userMemory);
			if (Objects.isNull(updatedUserMemory)) {
				throw DataQueryException.builder().message("Can't update correct count").dateTime(LocalDateTime.now())
						.debugMessage(log.getName()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			log.info("Finish updateCorrectCount");
		}
		throw DataQueryException.builder().message(MessageContant.INVALID_PARAM).dateTime(LocalDateTime.now())
				.debugMessage(log.getName()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@Override
	public List<UserMemory> findMissing(String userId, String categoryId, int pointLimit, int limitResult) {
		log.info("Find missing with user id = {}, category id = {}, point limit = {}, limit result = {}", 
				userId, categoryId, pointLimit, limitResult);
		return userMemoryRepository.findTopByUserIdAndStatementCategoryIdAndPointLessThan(limitResult, userId, categoryId, pointLimit);
	}

}
