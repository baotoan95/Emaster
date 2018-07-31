package com.emaster.dataquery.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.UserMemory;

public interface UserMemoryRepository extends MongoRepository<UserMemory, String> {
	Optional<UserMemory> findByUserEmailAndStatementId(String email, String statementId);
	List<UserMemory> findByUserEmailAndStatementCategoryId(String email, String categoryId);
	Optional<UserMemory> findByUserEmail(String email);
}
