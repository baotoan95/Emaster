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
import org.springframework.util.StringUtils;

import com.emaster.common.constant.MessageConstant;
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

	@Override
	public Page<Category> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		log.info("Start findAll (page={},size={})", pageNum, pageSize);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().status(HttpStatus.BAD_REQUEST)
			.dateTime(LocalDateTime.now())
					.message(MessageConstant.INVALID_PARAM).build();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Category> categoryPage = categoryRepository.findAllByOrderByCreatedDateDesc(pageable);
		
		log.info("Finish findAll");
		return categoryPage;
	}

	@Override
	public Category create(Category category) throws DataQueryException {
		if (Objects.nonNull(category) && Objects.nonNull(category.getCreatedBy())) {
			log.info("Start create with name={}", category.getName());
			Optional<User> user = userRepository.findByEmail(category.getCreatedBy().getEmail());
			if (user.isPresent()) {
				category.setId(null);
				category.setCreatedBy(user.get());
				category.setCreatedDate(new Date());
				category.setUpdatedDate(new Date());
				Category createdCategory = categoryRepository.save(category);
				log.info("Finish create");
				return createdCategory;
			} else {
				throw DataQueryException.builder().message(DataQueryMessage.DONT_HAVE_PERMIT_CREATE)
						.dateTime(LocalDateTime.now()).status(HttpStatus.FORBIDDEN).build();
			}
		} else {
			throw DataQueryException.builder().message(MessageConstant.INVALID_PARAM).dateTime(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public Category update(Category category) throws DataQueryException {
		if (Objects.nonNull(category) && Objects.nonNull(category.getId())) {
			log.info("Start update category with id={}", category.getId());
			Optional<Category> existedCategory = categoryRepository.findById(category.getId());
			if (existedCategory.isPresent()) {
				Category oldCategory = existedCategory.get();
				oldCategory.setUpdatedDate(new Date());
				oldCategory.setDefault(category.isDefault());
				oldCategory.setDescription(category.getDescription());
				oldCategory.setForkCount(category.getForkCount());
				oldCategory.setIcon(!StringUtils.isEmpty(category.getIcon()) ? category.getIcon() : oldCategory.getIcon());
				oldCategory.setName(category.getName());
				Category updatedCategory = categoryRepository.save(oldCategory);
				log.info("Finish update");
				return updatedCategory;
			} else {
				throw DataQueryException.builder()
				.message(DataQueryMessage.GIVEN_ID_NOT_EXISTED)
						.dateTime(LocalDateTime.now())
						.status(HttpStatus.BAD_REQUEST).build();
			}
		} else {
			throw DataQueryException.builder()
			.message(MessageConstant.INVALID_PARAM)
			.dateTime(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public Category findOne(String id) {
		log.info("Start findOne({})", id);
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return category.get();
		}
		log.info("Finish findOne");
		return null;
	}

	@Override
	public void delete(String id) {
		log.info("Start delete with id={}", id);
		categoryRepository.deleteById(id);
		log.info("Finish delete");
	}

	@Override
	public List<Category> findForASession(Optional<String> email) {
		log.info("Start findForASession({})", email.orElse(null));
		List<Category> categoriesByUser = new ArrayList<>();
		if (email.isPresent()) {
			categoriesByUser = categoryRepository.findByCreatedByEmailOrderByCreatedDateDesc(email.get());
		}
		List<Category> systemCategories = categoryRepository.findByIsDefaultOrderByCreatedDateDesc(true);
		List<Category> categories = Stream.concat(categoriesByUser.stream(), systemCategories.stream())
				.collect(Collectors.toList());
		log.info("Finish findForASession() size: {}", categories.size());
		return categories;
	}

}
