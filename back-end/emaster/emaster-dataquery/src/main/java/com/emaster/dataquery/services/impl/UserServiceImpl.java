package com.emaster.dataquery.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.PageDto;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.exception.NotFoundException;
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
	public PageDto<User> findAll(Optional<Integer> page, Optional<Integer> size) {
		int pageNum = page.orElse(0);
		int pageSize = page.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={}, size={})", pageNum, pageSize);
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
	public User update(User user) throws NotFoundException {
		log.debug("Start update with email {}", user.getEmail());
		if (userRepository.existsById(user.getId())) {
			User updateUser = userRepository.save(user);
			log.debug("Finish update");
			return updateUser;
		} else {
			throw new NotFoundException("The given id is not existed");
		}
	}

	@Override
	public void delete(String email) {
		log.debug("Start delete with email {}", email);
		userRepository.deleteByEmail(email);
		log.debug("Finish delete");
	}

}