package com.emaster.dataquery.facade.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.facade.CategoryFacade;
import com.emaster.dataquery.services.CategoryService;
import com.google.common.reflect.TypeToken;

public class CategoryFacadeImpl implements CategoryFacade {
	private CategoryService categoryService;
	private ModelMapper modelMapper;
	
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = new ModelMapper();
	}
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		Type pageDtoType = new TypeToken<PageDto<CategoryDto>>() {}.getType();
		return modelMapper.map(categoryService.findAll(page, size), pageDtoType);
	}

	@Override
	public CategoryDto create(CategoryDto category) throws DataQueryException {
		return null;
	}

	@Override
	public CategoryDto update(CategoryDto category) throws DataQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryDto> findForASession(Optional<String> userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
