package com.emaster.portal.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.service.StatementService;

@RestController
@RequestMapping("statements")
public class StatementController {
	private StatementService statementService;

	@Autowired
	public void setStatementService(StatementService statementService) {
		this.statementService = statementService;
	}

	@GetMapping
	public ResponseEntity<PageDto<StatementDto>> getAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws PortalException {
		return ResponseEntity.ok().body(statementService.findAll(page, size));
	}

	@PostMapping()
	public ResponseEntity<StatementDto> create(
			@RequestPart("statement") StatementDto statement,
			@RequestPart(value = "normalSoundFile", required = false) MultipartFile normalSoundFile,
			@RequestPart(value = "slowSoundFile", required = false) MultipartFile slowSoundFile,
			@RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws PortalException {
		return ResponseEntity.ok().body(statementService.create(statement, imageFile, normalSoundFile, slowSoundFile));
	}

	@PutMapping
	public ResponseEntity<StatementDto> update(@RequestBody StatementDto statementDto) throws PortalException {
		return ResponseEntity.ok().body(statementService.update(statementDto));
	}

	@GetMapping("/{statementId}")
	public ResponseEntity<StatementDto> findOne(@PathVariable("statementId") String statementId)
			throws PortalException {
		return ResponseEntity.ok().body(statementService.findOne(statementId));
	}

	@DeleteMapping("/{statementId}")
	public ResponseEntity<Void> delete(@PathVariable("statementId") String statementId) throws PortalException {
		statementService.delete(statementId);
		return ResponseEntity.ok().build();
	}
}
