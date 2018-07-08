package com.emaster.dataquery.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emaster.common.entities.User;
import com.emaster.dataquery.dal.UserDAL;
import com.emaster.dataquery.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDALImpl implements UserDAL {
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
	public Page<User> findAll(Pageable pageable) {
		log.debug("Start findAll (page={}, size={})", pageable.getPageNumber(), pageable.getPageSize());
		Page<User> userPage = userRepository.findAll(pageable);
		log.debug("Finish findAll");
		return userPage;
	}

	@Override
	public User create(User user) {
		log.debug("Start create user with email {}", user.getEmail());
		User createdUser = userRepository.save(user);
		log.debug("Start create user");
		return createdUser;
	}

	@Override
	public User update(User user) {
		log.debug("Start update user with email {}", user.getEmail());
		User updateUser = userRepository.save(user);
		log.debug("Finish update user");
		return updateUser;
	}

	@Override
	public boolean delete(String email) {
		log.debug("Start delete user with email {}", email);
		boolean isDeleted = false;
		User user = this.findOne(email);
		if (null != user) {
			try {
				userRepository.delete(user);
				isDeleted = true;
			} catch (Exception e) {
				log.error("Error: {}", e);
			}
		}
		log.debug("Finish delete user. Successed: {}", isDeleted);
		return isDeleted;
	}

}
