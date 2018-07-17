package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.constant.Point;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.entities.UserMemory;
import com.emaster.dataquery.repositories.UserMemoryRepository;
import com.emaster.dataquery.services.UserMemoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserMemorySerivceImpl implements UserMemoryService {
	@Autowired
	private UserMemoryRepository userMemoryRepository;
	
	@Override
	public PageDto<UserMemory> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.info("Start findAll({}, {})", pageNum, pageSize);
		if(!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder()
				.status(HttpStatus.BAD_REQUEST)
				.dateTime(LocalDateTime.now())
				.message(MessageContant.INVALID_PARAM)
				.build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		PageDto<UserMemory> pageDto = new PageDto<UserMemory>().build(userMemoryRepository.findAll(pageable));
		log.info("Finish findAll");
		return pageDto;
	}

	@Override
	public UserMemory findByUser(String userId) {
		log.info("Start findByUser({})", userId);
		Optional<UserMemory> userMemory = userMemoryRepository.findByUserId(userId);
		log.info("Finish findByUser");
		return userMemory.orElse(null);
	}

	@Override
	public List<UserMemory> findByUserAndCategory(String userId, String categoryId) {
		log.info("Start findByUserAndCategory({}, {})", userId, categoryId);
		List<UserMemory> users = userMemoryRepository.findByUserIdAndStatementCategoryId(userId, categoryId);
		log.info("Finish findByUserAndCategory");
		return users;
	}

	@Override
	public UserMemory findByUserAndStatement(String userId, String statementId) {
		log.info("Start findByUserAndStatement({}, {})", userId, statementId);
		Optional<UserMemory> userMemory = userMemoryRepository.findByUserIdAndStatementId(userId, statementId);
		log.info("Finish findByUserAndStatement");
		return userMemory.orElse(null);
	}

	@Override
	public UserMemory create(UserMemory userMemory) throws DataQueryException {
		if(Objects.isNull(userMemory) || StringUtils.isEmpty(userMemory.getStatement().getId()) || 
				StringUtils.isEmpty(userMemory.getUser().getId())) {
			throw DataQueryException.builder()
			.status(HttpStatus.BAD_REQUEST)
			.message("Statement ID and User ID are required")
			.dateTime(LocalDateTime.now()).build();
		}
		log.info("Start create with statementId = {}, userId = {}", userMemory.getStatement().getId(), userMemory.getUser().getId());
		userMemory.setStartLearnDate(new Date());
		userMemory.setLastLearnTime(new Date());
		UserMemory createdUserMemory = userMemoryRepository.save(userMemory);
		log.info("Finish create");
		return createdUserMemory;
	}

	@Override
	public void updateCorrectCount(String userId, String statementId, int correctCount) throws DataQueryException {
		log.info("Start updateCorrectCount({}, {}, {})", userId, statementId, correctCount);
		UserMemory userMemory = findByUserAndStatement(userId, statementId);
		if(Objects.nonNull(userMemory)) {
			userMemory.setCorrectCount(userMemory.getCorrectCount() + correctCount);
			userMemory.setIncorrectCount(correctCount < 0 ? userMemory.getIncorrectCount() - correctCount : userMemory.getIncorrectCount());
			userMemory.setPoint(userMemory.getPoint() + (correctCount * Point.POINT_PER_QUESTION));
			UserMemory updatedUserMemory = userMemoryRepository.save(userMemory);
			if(Objects.isNull(updatedUserMemory)) {
				throw DataQueryException.builder()
				.message("Can't update correct count")
				.dateTime(LocalDateTime.now())
				.debugMessage(log.getName())
				.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			log.info("Finish updateCorrectCount");
		}
		throw DataQueryException.builder()
		.message(MessageContant.INVALID_PARAM)
		.dateTime(LocalDateTime.now())
		.debugMessage(log.getName())
		.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}