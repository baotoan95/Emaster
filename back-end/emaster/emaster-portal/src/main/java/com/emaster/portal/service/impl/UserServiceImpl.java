package com.emaster.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.dal.UserDAL;
import com.emaster.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAL userDAL;

	@Override
	public PageDto<UserDto> getUsers(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		return userDAL.getUsers(page, size);
	}

	@Override
	public UserDto findOne(String email) throws PortalException {
		return userDAL.findOne(email);
	}

	@Override
	public UserDto create(UserDto userDto) throws PortalException {
		return userDAL.create(userDto);
	}

	@Override
	public UserDto update(UserDto userDto) throws PortalException {
		return userDAL.create(userDto);
	}

	@Override
	public void delete(String email) throws PortalException {
		userDAL.delete(email);
	}

}
