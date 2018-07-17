package com.emaster.dataquery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Category;
import com.emaster.dataquery.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<Page<Category>> findAll(@RequestParam(value = "page", required = false) Optional<Integer> page, 
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(categoryService.findAll(page, size));
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) throws DataQueryException {
		return ResponseEntity.ok().body(categoryService.create(category));
	}

	@PutMapping
	public ResponseEntity<Category> update(Category category) throws DataQueryException {
		return ResponseEntity.ok().body(categoryService.update(category));
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> findOne(@PathVariable("categoryId") String categoryId) {
		return ResponseEntity.ok().body(categoryService.findOne(categoryId));
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) {
		categoryService.delete(categoryId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user")
	public ResponseEntity<List<Category>> getListCategories(@RequestParam("id") String userId) {
		return ResponseEntity.ok().body(categoryService.findForASession(Optional.of(userId)));
	}
}
