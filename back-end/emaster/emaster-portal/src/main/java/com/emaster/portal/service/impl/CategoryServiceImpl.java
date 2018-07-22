package com.emaster.portal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.dal.CategoryDAL;
import com.emaster.portal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAL categoryDAL;
	
	@Override
	public PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		return categoryDAL.findAll(page, size);
	}

	@Override
	public CategoryDto create(CategoryDto category) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto update(CategoryDto category) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto findOne(String id) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws PortalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryDto> findForASession(Optional<String> userId) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

}
