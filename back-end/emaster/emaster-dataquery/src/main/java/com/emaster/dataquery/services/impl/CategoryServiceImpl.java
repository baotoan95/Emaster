package com.emaster.dataquery.services.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.repositories.CategoryRepository;
import com.emaster.dataquery.repositories.UserRepository;
import com.emaster.dataquery.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={},size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST).dateTime(LocalDateTime.now())
					.message(MessageContant.INVALID_PARAM).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Category> categoryPage = categoryRepository.findAll(pageable);
		
		Type type = new TypeToken<PageDto<CategoryDto>>() {}.getType();
		PageDto<CategoryDto> pageResult = modelMapper.map(categoryPage, type);
		log.debug("Finish findAll");
		return pageResult;
	}

	@Override
	public CategoryDto create(CategoryDto categoryDto) throws DataQueryException {
		if (Objects.nonNull(categoryDto)) {
			log.debug("Start create with name={}", categoryDto.getName());
			Optional<User> user = userRepository.findByEmail(categoryDto.getCreatedBy());
			if (user.isPresent()) {
				Category category = modelMapper.map(categoryDto, Category.class);
				category.setId(null);
				category.setCreatedBy(user.get().getEmail());
				category.setCreatedDate(new Date());
				category.setUpdatedDate(new Date());
				Category createdCategory = categoryRepository.save(category);
				CategoryDto createdCategoryDto = modelMapper.map(createdCategory, CategoryDto.class);
				log.debug("Finish create");
				return createdCategoryDto;
			} else {
				throw DataQueryException.builder().message(DataQueryMessage.DONT_HAVE_PERMIT_CREATE)
						.dateTime(LocalDateTime.now()).status(HttpStatus.FORBIDDEN).build();
			}
		} else {
			throw DataQueryException.builder().message(MessageContant.INVALID_PARAM).dateTime(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto) throws DataQueryException {
		if (Objects.nonNull(categoryDto)) {
			log.debug("Start update category with id={}", categoryDto.getId());
			Optional<Category> category = categoryRepository.findById(categoryDto.getId());
			if (category.isPresent()) {
				Category oldCategory = category.get();
				oldCategory.setUpdatedDate(new Date());
				oldCategory.setDefault(categoryDto.isDefault());
				oldCategory.setDescription(categoryDto.getDescription());
				oldCategory.setForkCount(categoryDto.getForkCount());
				oldCategory.setIcon(categoryDto.getIcon());
				oldCategory.setName(categoryDto.getName());
				Category updatedCategory = categoryRepository.save(oldCategory);
				CategoryDto updatedCategoryDto = modelMapper.map(updatedCategory, CategoryDto.class);
				log.debug("Finish update");
				return updatedCategoryDto;
			} else {
				throw DataQueryException.builder().message(DataQueryMessage.GIVEN_ID_NOT_EXISTED)
						.dateTime(LocalDateTime.now()).status(HttpStatus.BAD_REQUEST).build();
			}
		} else {
			throw DataQueryException.builder().message(MessageContant.BODY_REQUIRED).dateTime(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public CategoryDto findOne(String id) {
		log.debug("Start findOne({})", id);
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			CategoryDto categoryDto = modelMapper.map(category.get(), CategoryDto.class);
			return categoryDto;
		}
		log.debug("Finish findOne");
		return null;
	}

	@Override
	public void delete(String id) {
		log.debug("Start delete with id={}", id);
		categoryRepository.deleteById(id);
		log.debug("Finish delete");
	}

	@Override
	public List<CategoryDto> findForASession(Optional<String> userId) {
		log.debug("Start findForASession({})", userId);
		List<Category> categoriesByUser = new ArrayList<>();
		if (userId.isPresent()) {
			categoriesByUser = categoryRepository.findByCreatedBy(userId.get());
		}
		List<Category> systemCategories = categoryRepository.findByIsDefault(true);
		List<Category> categories = Stream.concat(categoriesByUser.stream(), systemCategories.stream())
				.collect(Collectors.toList());
		Type listCategoryDto = new TypeToken<List<CategoryDto>>() {
		}.getType();
		List<CategoryDto> listResult = modelMapper.map(categories, listCategoryDto);
		log.debug("Finish findForASession() size: {}", categories.size());
		return listResult;
	}

}
