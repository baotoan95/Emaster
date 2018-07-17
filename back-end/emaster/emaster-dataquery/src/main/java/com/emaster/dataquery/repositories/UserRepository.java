package com.emaster.dataquery.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
	void deleteByEmail(String email);
}
