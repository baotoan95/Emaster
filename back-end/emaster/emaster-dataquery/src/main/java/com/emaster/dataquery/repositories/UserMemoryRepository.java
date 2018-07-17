package com.emaster.dataquery.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.UserMemory;

public interface UserMemoryRepository extends MongoRepository<UserMemory, String> {
	Optional<UserMemory> findByUserIdAndStatementId(String userId, String statementId);
	List<UserMemory> findByUserIdAndStatementCategoryId(String userId, String categoryId);
	Optional<UserMemory> findByUserId(String userId);
}
