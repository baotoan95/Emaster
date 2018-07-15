package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
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
		User user = userRepository.findByEmail(email);
		log.debug("Finish findByEmail");
		return user;
	}

	@Override
	public PageDto<User> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={}, size={})", pageNum, pageSize);
		if(pageNum < 0 || pageSize <= 0) {
			throw DataQueryException.builder()
				.status(HttpStatus.BAD_REQUEST)
				.dateTime(LocalDateTime.now())
				.message(MessageContant.INVALID_PARAM)
				.build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> userPage = userRepository.findAll(pageable);
		PageDto<User> pageDto = new PageDto<>();
		pageDto.setCurrentPage(userPage.getNumber());
		pageDto.setPageSize(userPage.getSize());
		pageDto.setTotalPage(userPage.getTotalPages());
		pageDto.setContent(userPage.getContent());
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public User create(User user) {
		log.debug("Start create with email {}", user.getEmail());
		user.setId(null);
		User createdUser = userRepository.save(user);
		log.debug("Start create");
		return createdUser;
	}

	@Override
	public User update(User user) throws DataQueryException {
		log.debug("Start update with email {}", user.getEmail());
		if (userRepository.existsById(user.getId())) {
			User updateUser = userRepository.save(user);
			log.debug("Finish update");
			return updateUser;
		} else {
			throw DataQueryException.builder()
				.message("The given id is not existed")
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
