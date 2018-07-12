package com.emaster.portal.service;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;

public interface UserService {
	PageDto<UserDto> getUser(Optional<Integer> page, Optional<Integer> size);
	UserDto findByEmail(String email);
	UserDto create(UserDto userDto);
}
