package com.emaster.portal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	private CategoryService categoryService;
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public ResponseEntity<PageDto<CategoryDto>> findAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws PortalException {
		return ResponseEntity.ok().body(categoryService.findAll(page, size));
	}
	
	@PostMapping
	public ResponseEntity<CategoryDto> create(@ModelAttribute("categoryDto") CategoryDto categoryDto) throws PortalException {
		return ResponseEntity.ok().body(categoryService.create(categoryDto));
	}

	@PostMapping("/update")
	public ResponseEntity<CategoryDto> update(@ModelAttribute CategoryDto categoryDto) throws PortalException {
		return ResponseEntity.ok().body(categoryService.update(categoryDto));
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> findOne(@PathVariable("categoryId") String categoryId) throws PortalException {
		return ResponseEntity.ok().body(categoryService.findOne(categoryId));
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) throws PortalException {
		categoryService.delete(categoryId);
		return ResponseEntity.ok().build();
	}

}
