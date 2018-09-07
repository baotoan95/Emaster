package com.emaster.dataquery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.CreateUserMemoryDto;
import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.facade.UserMemoryFacade;

@RestController
@RequestMapping("userMemory")
public class UserMemoryController {
	@Autowired
	private UserMemoryFacade userMemoryFacade;
	
	@GetMapping("missingStatments")
	public ResponseEntity<List<UserMemoryDto>> getMessingStatements(
			@RequestParam("userId") String userId,
			@RequestParam("categoryId") String categoryId,
			@RequestParam("pointLimit") int pointLimit,
			@RequestParam("limitResult") int limitResult) {
		return ResponseEntity.ok(userMemoryFacade.findMissing(userId, categoryId, pointLimit, limitResult));
	}
	
	@PostMapping("addToMemory")
	public ResponseEntity<UserMemoryDto> addToMemory(@RequestBody CreateUserMemoryDto userMemory) throws DataQueryException {
		return ResponseEntity.ok(userMemoryFacade.create(userMemory.getUserId(), userMemory.getStatementId()));
	}
}
