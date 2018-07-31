package com.emaster.dataquery.facade;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.DataQueryException;

public interface UserFacade {
	UserDto findOne(String email);

	PageDto<UserDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	UserDto create(UserDto user) throws DataQueryException;

	UserDto update(UserDto user) throws DataQueryException;

	void delete(String email);
}
