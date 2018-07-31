package com.emaster.dataquery.facade;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;

public interface CategoryFacade {
	PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	CategoryDto create(CategoryDto category) throws DataQueryException;

	CategoryDto update(CategoryDto category) throws DataQueryException;

	CategoryDto findOne(String id);

	void delete(String id);

	List<CategoryDto> findForASession(Optional<String> userId);
}
