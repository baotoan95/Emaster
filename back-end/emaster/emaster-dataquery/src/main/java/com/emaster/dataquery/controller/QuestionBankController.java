package com.emaster.dataquery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.facade.QuestionBankFacade;

@RestController
@RequestMapping("questionBank")
public class QuestionBankController {
	@Autowired
	private QuestionBankFacade questionBankFacade;
	
	@GetMapping("/generateByCategory")
	public ResponseEntity<List<StatementDto>> generateByCategoryId(@RequestParam("userId") String userId, 
			@RequestParam("categoryId") String categoryId, int limitQuestions) throws DataQueryException {
		return ResponseEntity.ok(questionBankFacade.generateByCategory(userId, categoryId, limitQuestions));
	}
}
