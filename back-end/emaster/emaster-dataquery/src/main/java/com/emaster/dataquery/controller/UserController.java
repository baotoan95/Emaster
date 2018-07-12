package com.emaster.dataquery.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.PageDto;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<PageDto<User>> getUsers(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) {
		return ResponseEntity.ok().body(userService.findAll(page, size));
	}

	@PostMapping
	public ResponseEntity<User> getUsers(@RequestBody User user) {
		return ResponseEntity.ok().body(userService.create(user));
	}
}
