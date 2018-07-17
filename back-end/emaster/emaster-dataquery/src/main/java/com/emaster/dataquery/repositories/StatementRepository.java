package com.emaster.dataquery.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.Statement;

public interface StatementRepository extends MongoRepository<Statement, String> {
	List<Statement> findByCreatedByIdAndCategoryId(String userId, String categoryId);
}