package com.emaster.dataquery.facade.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;
import com.emaster.dataquery.entities.UserMemory;
import com.emaster.dataquery.facade.UserMemoryFacade;
import com.emaster.dataquery.services.UserMemoryService;

@Component
public class UserMemoryFacadeImpl implements UserMemoryFacade {
	@Autowired
	private UserMemoryService userMemoryService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<UserMemoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		return null;
	}

	@Override
	public UserMemoryDto findByUser(String userId) {
		return null;
	}

	@Override
	public List<UserMemoryDto> findByUserAndCategory(String userId, String categoryId) {
		return null;
	}

	@Override
	public UserMemoryDto findByUserAndStatement(String userId, String statementId) {
		return null;
	}

	@Override
	public UserMemoryDto create(String userId, String statementId) throws DataQueryException {
		UserMemory userMemory = userMemoryService.create(userId, statementId);
		if(Objects.nonNull(userMemory)) {
			return modelMapper.map(userMemory, UserMemoryDto.class);
		}
		return null;
	}

	@Override
	public void updateCorrectCount(String userId, String statementId, int correctCount) throws DataQueryException {
		
	}

	@Override
	public List<UserMemoryDto> findMissing(String userId, String categoryId, int lessThanOrEqual) {
		List<UserMemory> memories = userMemoryService.findMissing(userId, categoryId, lessThanOrEqual);
		return memories.stream().map(userMemory -> {
			Statement statement = Statement.builder()
					.id(userMemory.getStatement().getId())
					.content(userMemory.getStatement().getContent()).build();
			userMemory.setStatement(statement);
			return modelMapper.map(userMemory, UserMemoryDto.class);
		}).collect(Collectors.toList());
	}

}
