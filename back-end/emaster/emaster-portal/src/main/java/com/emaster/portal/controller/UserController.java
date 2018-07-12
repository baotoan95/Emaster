package com.emaster.portal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.portal.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<PageDto<UserDto>> findOne(@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) {
		return ResponseEntity.ok().body(userService.getUser(page, size));
	}
}
