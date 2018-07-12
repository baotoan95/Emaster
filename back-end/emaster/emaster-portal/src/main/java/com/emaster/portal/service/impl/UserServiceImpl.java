package com.emaster.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.portal.dal.UserDAL;
import com.emaster.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAL userDAL;

	@Override
	public UserDto findByEmail(String email) {
		return null;
	}

	@Override
	public UserDto create(UserDto userDto) {
		return null;
	}

	@Override
	public PageDto<com.emaster.common.dto.UserDto> getUser(Optional<Integer> page, Optional<Integer> size) {
		return userDAL.getUsers(page, size);
	}

}
