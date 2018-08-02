package com.emaster.dataquery.facade.impl;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.facade.UserFacade;
import com.emaster.dataquery.services.UserService;

@Component
public class UserFacadeImpl implements UserFacade {
	private UserService userService;
	private ModelMapper modelMapper;
	
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDto findOne(String email) {
		User user = userService.findOne(email);
		if(Objects.nonNull(user)) {
			return modelMapper.map(user, UserDto.class);
		}
		return null;
	}

	@Override
	public PageDto<UserDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		Page<User> pageUser = userService.findAll(page, size);
		Type pageUserDtoType = new TypeToken<PageDto<UserDto>>() {}.getType();
		PageDto<UserDto> pageUserDto = modelMapper.map(pageUser, pageUserDtoType);
		pageUserDto.setContent(pageUser.getContent().stream().map(user -> {
			return modelMapper.map(user, UserDto.class);
		}).collect(Collectors.toList()));
		return pageUserDto;
	}

	@Override
	public UserDto create(UserDto userDto) throws DataQueryException {
		User user = modelMapper.map(userDto, User.class);
		return modelMapper.map(userService.create(user), UserDto.class);
	}

	@Override
	public UserDto update(UserDto userDto) throws DataQueryException {
		User user = modelMapper.map(userDto, User.class);
		return modelMapper.map(userService.update(user), UserDto.class);
	}

	@Override
	public void delete(String email) {
		userService.delete(email);
	}

}
