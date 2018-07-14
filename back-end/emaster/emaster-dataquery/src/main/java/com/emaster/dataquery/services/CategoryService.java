package com.emaster.dataquery.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.exception.NotFoundException;

public interface CategoryService {
	Page<Category> findAll(Pageable pageable);

	Category create(Category category);

	Category update(Category category) throws NotFoundException;

	Category findOne(String id);

	void delete(String id);
}
