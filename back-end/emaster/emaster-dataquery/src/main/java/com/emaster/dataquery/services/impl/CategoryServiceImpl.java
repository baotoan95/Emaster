package com.emaster.dataquery.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.exception.NotFoundException;
import com.emaster.dataquery.repositories.CategoryRepository;
import com.emaster.dataquery.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<Category> findAll(Pageable pageable) {
		log.debug("Start findAll (page={},size={})", pageable.getPageNumber(), pageable.getPageSize());
		Page<Category> categoryPage = categoryRepository.findAll(pageable);
		log.debug("Finish findAll");
		return categoryPage;
	}

	@Override
	public Category create(Category category) {
		log.debug("Start create with name={}", category.getName());
		category.setId(null);
		Category createdCategory = categoryRepository.save(category);
		log.debug("Finish create");
		return createdCategory;
	}

	@Override
	public Category update(Category category) throws NotFoundException {
		log.debug("Start update category with id={}", category.getId());
		if (categoryRepository.existsById(category.getId())) {
			Category updatedCategory = categoryRepository.save(category);
			log.debug("Finish update");
			return updatedCategory;
		} else {
			throw new NotFoundException(DataQueryMessage.GIVEN_ID_NOT_EXISTED);
		}
	}

	@Override
	public Category findOne(String id) {
		log.debug("Start findOne({})", id);
		Optional<Category> result = categoryRepository.findById(id);
		log.debug("Finish findOne");
		return result.orElse(null);
	}

	@Override
	public void delete(String id) {
		log.debug("Start delete with id={}", id);
		categoryRepository.deleteById(id);
		log.debug("Finish delete");
	}

}
