package com.emaster.dataquery.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
import com.emaster.common.dto.QuestionBankDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.facade.QuestionBankFacade;

@RestController
@RequestMapping("question")
public class QuestionBankController {
	private QuestionBankFacade questionBankFacade;

	@Autowired
	public void setQuestionBankFacade(QuestionBankFacade questionBankFacade) {
		this.questionBankFacade = questionBankFacade;
	}

	@GetMapping
	public ResponseEntity<PageDto<QuestionBankDto>> findAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(questionBankFacade.findAll(page, size));

	}
	
	@PostMapping
	public ResponseEntity<QuestionBankDto> create(@RequestBody QuestionBankDto questionBankDto)throws DataQueryException{
		return ResponseEntity.ok().body(questionBankFacade.create(questionBankDto));
	}
	
	@GetMapping("/{questionBankId}")
	public ResponseEntity<QuestionBankDto> findOne(@PathVariable ("questionBankId") String questionBankId){
		
		
		return ResponseEntity.ok().body(questionBankFacade.findOne(questionBankId));
	} 
	 
	@PutMapping
	public ResponseEntity<QuestionBankDto> update (@RequestBody QuestionBankDto questionBankDto) throws DataQueryException{
		return ResponseEntity.ok().body(questionBankFacade.update(questionBankDto));
	}
	
	@DeleteMapping("/{questionBankId}")
	public ResponseEntity<Void> delete (@PathVariable("questionBankId") String questionBankId){
		questionBankFacade.delete(questionBankId);
		return ResponseEntity.ok().build();
	}

}
