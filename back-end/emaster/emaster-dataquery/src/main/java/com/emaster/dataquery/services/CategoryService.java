package com.emaster.dataquery.services;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.exception.NotFoundException;

public interface CategoryService {
	Page<Category> findAll(Pageable pageable);

	Category create(Category category);

	Category update(Category category) throws NotFoundException;

	Category findOne(ObjectId id);

	void delete(ObjectId id);
}
