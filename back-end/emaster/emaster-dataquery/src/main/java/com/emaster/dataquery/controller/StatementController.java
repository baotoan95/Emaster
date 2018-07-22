package com.emaster.dataquery.controller;

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
import com.emaster.dataquery.services.StatementService;

@RestController
@RequestMapping("statements")
public class StatementController {
	@Autowired
	private StatementService statementService;
	
	@GetMapping
	public ResponseEntity<PageDto<StatementDto>> getAll(@RequestParam(value = "page", required = false) Optional<Integer> page, 
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(statementService.findAll(page, size));
	}
	
	@PostMapping
	public ResponseEntity<StatementDto> create(@RequestBody StatementDto statement) throws DataQueryException {
		return ResponseEntity.ok().body(statementService.create(statement));
	}
	
	@PutMapping
	public ResponseEntity<StatementDto> update(StatementDto statement) throws DataQueryException {
		return ResponseEntity.ok().body(statementService.update(statement));
	}

	@GetMapping("/{statementId}")
	public ResponseEntity<StatementDto> findOne(@PathVariable("statementId") String statementId) {
		return ResponseEntity.ok().body(statementService.findOne(statementId));
	}
	
	@DeleteMapping("/{statementId}")
	public ResponseEntity<Void> delete(@PathVariable("statementId") String statementId) {
		statementService.delete(statementId);
		return ResponseEntity.ok().build();
	}
}
