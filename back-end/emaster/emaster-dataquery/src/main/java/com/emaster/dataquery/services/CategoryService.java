package com.emaster.dataquery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Category;

public interface CategoryService {
	Page<Category> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	Category create(Category category) throws DataQueryException;

	Category update(Category category) throws DataQueryException;

	Category findOne(String id);

	void delete(String id);

	List<Category> findForASession(Optional<String> userId);
}
