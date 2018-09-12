package com.emaster.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.QuestionDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.service.QuestionBankService;

@RestController
@RequestMapping("questionBank")
public class QuestionBankController {
	@Autowired
	private QuestionBankService questionBankService;
	
	@GetMapping("generateByCategory")
	public ResponseEntity<List<QuestionDto>> generateQuestionByCategory(
			@RequestParam("categoryId") String categoryId) throws PortalException {
		return ResponseEntity.ok(questionBankService.generateQuestionByCategory(categoryId));
	}
}
