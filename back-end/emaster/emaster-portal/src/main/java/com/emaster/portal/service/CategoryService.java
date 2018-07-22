package com.emaster.portal.service;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;

public interface CategoryService {
	PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException;

	CategoryDto create(CategoryDto category) throws PortalException;

	CategoryDto update(CategoryDto category) throws PortalException;

	CategoryDto findOne(String id) throws PortalException;

	void delete(String id) throws PortalException;

	List<CategoryDto> findForASession(Optional<String> userId) throws PortalException;
}
