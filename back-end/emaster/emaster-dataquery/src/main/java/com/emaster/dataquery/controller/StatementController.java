package com.emaster.dataquery.controller;

import java.util.Arrays;
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

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.facade.StatementFacade;

@RestController
@RequestMapping("statements")
public class StatementController {
	@Autowired
	private StatementFacade statementFacade;
	
	@GetMapping
	public ResponseEntity<PageDto<StatementDto>> getAll(@RequestParam(value = "page", required = false) Optional<Integer> page, 
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(statementFacade.findAll(page, size));
	}
	
	@PostMapping
	public ResponseEntity<StatementDto> create(@RequestBody StatementDto statement) throws DataQueryException {
		return ResponseEntity.ok().body(statementFacade.create(statement));
	}
	
	@PutMapping
	public ResponseEntity<StatementDto> update(@RequestBody StatementDto statement) throws DataQueryException {
		return ResponseEntity.ok().body(statementFacade.update(statement));
	}

	@GetMapping("/{statementId}")
	public ResponseEntity<StatementDto> findOne(@PathVariable("statementId") String statementId) {
		return ResponseEntity.ok().body(statementFacade.findOne(statementId));
	}
	
	@DeleteMapping("/{statementId}")
	public ResponseEntity<Void> delete(@PathVariable("statementId") String statementId) {
		statementFacade.delete(statementId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<StatementDto>> search(@RequestParam("content") String content) {
		return ResponseEntity.ok(statementFacade.search(content));
	}
	
	@GetMapping("/category")
	public ResponseEntity<PageDto<StatementDto>> findByCategory(
			@RequestParam(value = "id", required = true) String categoryId,
			@RequestParam(value = "page", required = false) Optional<Integer> page, 
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok(statementFacade.findByCategory(categoryId, page, size));
	}
	
	@GetMapping("/categoryExcept")
	public ResponseEntity<PageDto<StatementDto>> findByCategory(
			@RequestParam(value = "id", required = true) String categoryId,
			@RequestParam(value = "limit", required = true) int limit, 
			@RequestParam(value = "excepted", required = true) String excepted) throws DataQueryException {
				List<String> exceptedList = Arrays.asList(excepted.split(","));
		return ResponseEntity.ok(statementFacade.findByCategoryExcepting(categoryId, limit, exceptedList));
	}
}
