package com.emaster.dataquery.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	User findByEmail(String email);

	void deleteByEmail(String email);
}
