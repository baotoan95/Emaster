package com.emaster.portal.service;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;

public interface UserService {
	PageDto<UserDto> getUsers(Optional<Integer> page, Optional<Integer> size) throws PortalException;
	
	UserDto findOne(String email) throws PortalException;

	UserDto create(UserDto userDto) throws PortalException;

	UserDto update(UserDto userDto) throws PortalException;

	void delete(String email) throws PortalException;
}
