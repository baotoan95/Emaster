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
import com.emaster.dataquery.facade.CategoryFacade;

@RestController
@RequestMapping("categories")
public class CategoryController {
	private CategoryFacade categoryFacade;
	
	@Autowired
	public void setCategoryFacade(CategoryFacade categoryFacade) {
		this.categoryFacade = categoryFacade;
	}

	@GetMapping
	public ResponseEntity<PageDto<CategoryDto>> findAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(categoryFacade.findAll(page, size));
	}

	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) throws DataQueryException {
		return ResponseEntity.ok().body(categoryFacade.create(categoryDto));
	}

	@PutMapping
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) throws DataQueryException {
		return ResponseEntity.ok().body(categoryFacade.update(categoryDto));
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> findOne(@PathVariable("categoryId") String categoryId) {
		return ResponseEntity.ok().body(categoryFacade.findOne(categoryId));
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) {
		categoryFacade.delete(categoryId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user")
	public ResponseEntity<List<CategoryDto>> getListCategories(@RequestParam("email") String email) {
		return ResponseEntity.ok().body(categoryFacade.findForASession(Optional.of(email)));
	}
}
