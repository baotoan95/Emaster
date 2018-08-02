package com.emaster.dataquery.facade.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.facade.CategoryFacade;
import com.emaster.dataquery.services.CategoryService;

@Component
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
		Page<Category> pageCategory = categoryService.findAll(page, size);
		PageDto<CategoryDto> pageDto = modelMapper.map(pageCategory, pageDtoType);
		pageDto.setContent(pageCategory.stream().map(category -> {
			return modelMapper.map(category, CategoryDto.class);
		}).collect(Collectors.toList()));
		return pageDto;
	}

	@Override
	public CategoryDto create(CategoryDto categoryDto) throws DataQueryException {
		Category category = modelMapper.map(categoryDto, Category.class);
		return modelMapper.map(categoryService.create(category), CategoryDto.class);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto) throws DataQueryException {
		Category category = modelMapper.map(categoryDto, Category.class);
		return modelMapper.map(categoryService.update(category), CategoryDto.class);
	}

	@Override
	public CategoryDto findOne(String id) {
		Category category = categoryService.findOne(id);
		if(Objects.nonNull(category)) {
			return modelMapper.map(category, CategoryDto.class);
		}
		return null;
	}

	@Override
	public void delete(String id) {
		categoryService.delete(id);
	}

	@Override
	public List<CategoryDto> findForASession(Optional<String> userId) {
		return null;
	}
	
}
