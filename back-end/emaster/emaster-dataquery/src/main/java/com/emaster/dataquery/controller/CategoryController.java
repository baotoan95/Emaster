package com.emaster.dataquery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<PageDto<CategoryDto>> findAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(categoryService.findAll(page, size));
	}

	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto category) throws DataQueryException {
		return ResponseEntity.ok().body(categoryService.create(category));
	}

	@PutMapping
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto category) throws DataQueryException {
		return ResponseEntity.ok().body(categoryService.update(category));
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> findOne(@PathVariable("categoryId") String categoryId) {
		return ResponseEntity.ok().body(categoryService.findOne(categoryId));
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) {
		categoryService.delete(categoryId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user")
	public ResponseEntity<List<CategoryDto>> getListCategories(@RequestParam("id") String userId) {
		return ResponseEntity.ok().body(categoryService.findForASession(Optional.of(userId)));
	}
}
