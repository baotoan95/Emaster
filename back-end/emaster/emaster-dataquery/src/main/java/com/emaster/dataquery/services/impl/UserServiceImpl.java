package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.emaster.common.constant.MessageContant;
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

	@Override
	public User findOne(String email) {
		log.debug("Start findByEmail ({})", email);
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			log.debug("Finish findByEmail");
			return user.get();
		}
		return null;
	}

	@Override
	public Page<User> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={}, size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).dateTime(LocalDateTime.now())
					.message(MessageContant.INVALID_PARAM).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> pageUser = userRepository.findAll(pageable);
		log.debug("Finish findAll");
		return pageUser;
	}

	@Override
	public User create(User user) throws DataQueryException {
		if (user == null || StringUtils.isEmpty(user.getEmail())) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).message(MessageContant.INVALID_PARAM)
					.dateTime(LocalDateTime.now()).build();
		}
		log.debug("Start create with email {}", user.getEmail());
		user.setId(null);
		user.setCreateDate(new Date());
		user.setUpdatedDate(new Date());
		User createdUser = userRepository.save(user);
		log.debug("Finish create");
		return createdUser;
	}

	@Override
	public User update(User user1) throws DataQueryException {
		log.debug("Start update with email {}", user1.getEmail());
		Optional<User> user = userRepository.findByEmail(user1.getEmail());
		if (user.isPresent()) {
			User oldUser = user.get();
			user1.setCreateDate(oldUser.getCreateDate());
			user1.setUpdatedDate(oldUser.getUpdatedDate());
			User updatedUser = userRepository.save(user1);
			log.debug("Finish update");
			return updatedUser;
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
