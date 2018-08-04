package com.emaster.portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.ChallengeDto;

@RestController
@RequestMapping("challenges")
public class ChallengeController {
	public ResponseEntity<ChallengeDto> getChallengeBasedCategory(@RequestParam("category") String categoryId) {
		return null;
	}
}
