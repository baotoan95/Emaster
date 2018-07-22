package com.emaster.dataquery.services.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
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
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StatementDto create(StatementDto statementDto) throws DataQueryException {
		log.debug("Start create");
		if (Objects.nonNull(statementDto)) {
			Optional<User> user = userRepository.findByEmail(statementDto.getCreatedBy());
			if (user.isPresent()) {
				statementDto.setId(null);
				statementDto.setCreatedDate(new Date());
				statementDto.setCreatedBy(user.get().getEmail());
				Statement newStatement = modelMapper.map(statementDto, Statement.class);
				Statement createdStatement = statementRepository.save(newStatement);
				StatementDto createdStatementDto = modelMapper.map(createdStatement, StatementDto.class);
				log.debug("Finish create");
				return createdStatementDto;
			} else {
				throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST)
						.message(DataQueryMessage.DONT_HAVE_PERMIT_CREATE).dateTime(LocalDateTime.now()).build();
			}
		}
		throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).message(MessageContant.INVALID_PARAM)
				.dateTime(LocalDateTime.now()).build();
	}

	@Override
	public PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll(page={},size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().message(MessageContant.INVALID_PARAM).status(HttpStatus.BAD_REQUEST)
					.dateTime(LocalDateTime.now()).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Statement> pageStatement = statementRepository.findAll(pageable);
		Type pageSatementDtoType = new TypeToken<PageDto<StatementDto>>() {
		}.getClass();
		PageDto<StatementDto> pageDto = modelMapper.map(pageStatement, pageSatementDtoType);
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public StatementDto update(StatementDto statementDto) throws DataQueryException {
		Optional<Statement> statement = statementRepository.findById(statementDto.getId());
		if (statement.isPresent()) {
			log.debug("Start update with id={}", statementDto.getId());
			Statement newStatement = modelMapper.map(statementDto, Statement.class);
			Statement updatedStatement = statementRepository.save(newStatement);
			StatementDto updatedStatementDto = modelMapper.map(updatedStatement, StatementDto.class);
			log.debug("Finish update");
			return updatedStatementDto;
		}
		throw DataQueryException.builder().message("The given id is not existed").status(HttpStatus.BAD_REQUEST)
				.dateTime(LocalDateTime.now()).build();
	}

	@Override
	public StatementDto findOne(String id) {
		log.debug("Start findOne ({})", id);
		Optional<Statement> result = statementRepository.findById(id);
		if (result.isPresent()) {
			log.debug("Finish findOne");
			return modelMapper.map(result.get(), StatementDto.class);
		}
		return null;
	}

	@Override
	public void delete(String id) {
		log.debug("Start delete with id={}", id);
		statementRepository.deleteById(id);
		log.debug("Finish delete");
	}

	@Override
	public List<StatementDto> getStatementsForASession(String userId, String categoryId) {
		log.debug("Start getStatementsForASession({}, {})", userId, categoryId);
		List<Statement> statements = statementRepository.findByCreatedByAndCategory(userId, categoryId);
		Type listStatementDtoType = new TypeToken<List<StatementDto>>() {
		}.getClass();
		List<StatementDto> statementDtos = modelMapper.map(statements, listStatementDtoType);
		log.debug("Finished getStatementsForASession() size: {}", statements.size());
		return statementDtos;
	}

}
