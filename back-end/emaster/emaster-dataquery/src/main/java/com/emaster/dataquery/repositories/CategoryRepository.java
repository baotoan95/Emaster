package com.emaster.dataquery.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
	List<Category> findByCreatedByEmailOrderByCreatedDateDesc(String email);
	List<Category> findByIsDefaultOrderByCreatedDateDesc(boolean isDefault);
	Page<Category> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
