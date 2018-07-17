package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.repositories.UserRepository;
import com.emaster.dataquery.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOne(String email) {
		log.debug("Start findByEmail ({})", email);
		Optional<User> user = userRepository.findByEmail(email);
		log.debug("Finish findByEmail");
		return user.orElse(null);
	}

	@Override
	public PageDto<User> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={}, size={})", pageNum, pageSize);
		if(!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder()
				.status(HttpStatus.BAD_REQUEST)
				.dateTime(LocalDateTime.now())
				.message(MessageContant.INVALID_PARAM)
				.build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		PageDto<User> pageDto = new PageDto<User>().build(userRepository.findAll(pageable));
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public User create(User user) throws DataQueryException {
		if(user == null) {
			throw DataQueryException.builder()
			.status(HttpStatus.BAD_REQUEST)
			.message(MessageContant.INVALID_PARAM)
			.dateTime(LocalDateTime.now())
			.build();
		}
		log.debug("Start create with email {}", user.getEmail());
		user.setId(null);
		user.setCreateDate(new Date());
		User createdUser = userRepository.save(user);
		log.debug("Finish create");
		return createdUser;
	}

	@Override
	public User update(User user) throws DataQueryException {
		log.debug("Start update with email {}", user.getEmail());
		if (user.getId() != null && userRepository.existsById(user.getId())) {
			User updateUser = userRepository.save(user);
			log.debug("Finish update");
			return updateUser;
		} else {
			throw DataQueryException.builder()
				.message("The given id is null or it's not existed")
				.status(HttpStatus.NOT_FOUND)
				.dateTime(LocalDateTime.now())
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
