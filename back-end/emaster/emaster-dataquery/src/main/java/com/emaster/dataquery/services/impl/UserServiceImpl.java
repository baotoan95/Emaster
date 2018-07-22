package com.emaster.dataquery.services.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.repositories.UserRepository;
import com.emaster.dataquery.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto findOne(String email) {
		log.debug("Start findByEmail ({})", email);
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			log.debug("Finish findByEmail");
			return modelMapper.map(user, UserDto.class);
		}
		return null;
	}

	@Override
	public PageDto<UserDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={}, size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).dateTime(LocalDateTime.now())
					.message(MessageContant.INVALID_PARAM).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> pageUser = userRepository.findAll(pageable);
		Type pageUserDtoType = new TypeToken<PageDto<UserDto>>() {
		}.getClass();
		PageDto<UserDto> pageDto = modelMapper.map(pageUser, pageUserDtoType);
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public UserDto create(UserDto userDto) throws DataQueryException {
		if (userDto == null || StringUtils.isEmpty(userDto.getEmail())) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).message(MessageContant.INVALID_PARAM)
					.dateTime(LocalDateTime.now()).build();
		}
		log.debug("Start create with email {}", userDto.getEmail());
		User user = modelMapper.map(userDto, User.class);
		user.setId(null);
		user.setCreateDate(new Date());
		User createdUser = userRepository.save(user);
		UserDto createdUserDto = modelMapper.map(createdUser, UserDto.class);
		log.debug("Finish create");
		return createdUserDto;
	}

	@Override
	public UserDto update(UserDto userDto) throws DataQueryException {
		log.debug("Start update with email {}", userDto.getEmail());
		Optional<User> user = userRepository.findByEmail(userDto.getEmail());
		if (user.isPresent()) {
			User oldUser = user.get();
			User newUser = modelMapper.map(userDto, User.class);
			newUser.setCreateDate(oldUser.getCreateDate());
			User updatedUser = userRepository.save(newUser);
			UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);
			log.debug("Finish update");
			return updatedUserDto;
		} else {
			throw DataQueryException.builder().message(DataQueryMessage.GIVEN_ID_NOT_EXISTED)
					.status(HttpStatus.NOT_FOUND).dateTime(LocalDateTime.now())
					.debugMessage(log.getName() + " Update user error").build();
		}
	}

	@Override
	public void delete(String email) {
		log.debug("Start delete with email {}", email);
		userRepository.deleteByEmail(email);
		log.debug("Finish delete");
	}

}
