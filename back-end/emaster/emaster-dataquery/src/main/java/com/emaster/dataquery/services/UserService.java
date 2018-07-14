package com.emaster.dataquery.services;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.User;

public interface UserService {
	User findOne(String email);

	PageDto<User> findAll(Optional<Integer> page, Optional<Integer> size);

	User create(User user);

	User update(User user) throws DataQueryException;

	void delete(String email);
}
