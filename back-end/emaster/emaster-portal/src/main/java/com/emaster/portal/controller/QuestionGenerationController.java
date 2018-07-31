package com.emaster.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.StatementDto;
import com.emaster.portal.service.QuestionService;

@RestController
@RequestMapping("questionGenerator")
public class QuestionGenerationController {
	@Autowired
	private QuestionService questionService;
	@Value("${limitQuestionPerSession}")
	private int limitQuestionPerSession;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<StatementDto>> getQuestionsForASession(@PathVariable("userId") String userId,
			@RequestParam("cate") String categoryId) {
		return ResponseEntity.ok().body(questionService.generateQuestions(userId, categoryId, limitQuestionPerSession));
	}
}
