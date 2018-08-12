package com.emaster.dataquery.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.User;

public interface UserService {
	User findOne(String email);

	Page<User> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	User create(User user) throws DataQueryException;

	User update(User user) throws DataQueryException;

	void delete(String email);
}
