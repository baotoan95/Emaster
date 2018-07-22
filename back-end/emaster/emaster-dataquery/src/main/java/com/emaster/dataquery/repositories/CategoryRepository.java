package com.emaster.dataquery.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
	List<Category> findByCreatedBy(String email);
	List<Category> findByIsDefault(boolean isDefault);
}
