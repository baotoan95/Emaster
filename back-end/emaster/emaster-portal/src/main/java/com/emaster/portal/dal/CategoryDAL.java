package com.emaster.portal.dal;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.exception.PortalException;

public interface CategoryDAL {
	PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException;

	CategoryDto create(CategoryDto category) throws DataQueryException;

	CategoryDto update(CategoryDto category) throws DataQueryException;

	CategoryDto findOne(String id);

	void delete(String id);

	List<CategoryDto> findForASession(Optional<String> userId);
}
