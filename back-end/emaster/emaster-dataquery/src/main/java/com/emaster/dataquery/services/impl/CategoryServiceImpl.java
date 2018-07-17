package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.repositories.CategoryRepository;
import com.emaster.dataquery.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<Category> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = page.orElse(Integer.MAX_VALUE);
		log.debug("Start findAll (page={},size={})", pageNum, pageSize);
		if(!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder()
			.status(HttpStatus.BAD_REQUEST)
			.dateTime(LocalDateTime.now())
			.message(MessageContant.INVALID_PARAM)
			.build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Category> categoryPage = categoryRepository.findAll(pageable);
		log.debug("Finish findAll");
		return categoryPage;
	}

	@Override
	public Category create(Category category) throws DataQueryException {
		if(Objects.nonNull(category)) {
			log.debug("Start create with name={}", category.getName());
			category.setId(null);
			Category createdCategory = categoryRepository.save(category);
			log.debug("Finish create");
			return createdCategory;
		}
		throw DataQueryException.builder()
		.message(MessageContant.INVALID_PARAM)
		.dateTime(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST).build();
	}

	@Override
	public Category update(Category category) throws DataQueryException {
		if (Objects.nonNull(category) && categoryRepository.existsById(category.getId())) {
			log.debug("Start update category with id={}", category.getId());
			category.setUpdatedDate(new Date());
			Category updatedCategory = categoryRepository.save(category);
			log.debug("Finish update");
			return updatedCategory;
		} else {
			throw DataQueryException.builder()
			.message(DataQueryMessage.GIVEN_ID_NOT_EXISTED)
			.dateTime(LocalDateTime.now())
			.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public Category findOne(String id) {
		log.debug("Start findOne({})", id);
		Optional<Category> category = categoryRepository.findById(id);
		log.debug("Finish findOne");
		return category.orElse(null);
	}

	@Override
	public void delete(String id) {
		log.debug("Start delete with id={}", id);
		categoryRepository.deleteById(id);
		log.debug("Finish delete");
	}

	@Override
	public List<Category> findForASession(Optional<String> userId) {
		log.debug("Start findForASession({})", userId);
		List<Category> categoriesByUser = new ArrayList<>();
		if (userId.isPresent()) {
			categoriesByUser = categoryRepository.findByCreatedById(userId.get());
		}
		List<Category> systemCategories = categoryRepository.findByIsDefault(true);
		List<Category> categories = Stream.concat(categoriesByUser.stream(), systemCategories.stream())
				.collect(Collectors.toList());
		log.debug("Finish findForASession() size: {}", categories.size());
		return categories;
	}

}
