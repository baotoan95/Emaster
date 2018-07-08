package com.emaster.dataquery.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.emaster.common.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
	User findByEmail(String email);
}
