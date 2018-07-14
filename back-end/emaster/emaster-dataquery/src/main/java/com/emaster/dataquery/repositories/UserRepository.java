package com.emaster.dataquery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByEmail(String email);

	void deleteByEmail(String email);
}
