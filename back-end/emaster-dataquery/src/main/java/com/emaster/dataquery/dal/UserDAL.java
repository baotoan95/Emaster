package com.emaster.dataquery.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emaster.common.entities.User;

public interface UserDAL {
	User findOne(String email);

	Page<User> findAll(Pageable pageable);
	
	User create(User user);
	
	User update(User user);
	
	boolean delete(String email);
}
