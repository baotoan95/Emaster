package com.emaster.portal.service;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;

public interface UserService {
	PageDto<UserDto> getUser(Optional<Integer> page, Optional<Integer> size) throws PortalException;
	UserDto findByEmail(String email);
	UserDto create(UserDto userDto);
}
