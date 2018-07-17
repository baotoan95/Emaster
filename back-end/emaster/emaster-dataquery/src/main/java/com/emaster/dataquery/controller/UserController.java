package com.emaster.dataquery.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageDto<User>> getUsers(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(userService.findAll(page, size));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUsers(@RequestBody User user) throws DataQueryException {
		return ResponseEntity.ok().body(userService.create(user));
	}

	@GetMapping(value = "/{email:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok().body(userService.findOne(email));
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) throws DataQueryException {
		return ResponseEntity.ok().body(userService.update(user));
	}

	@DeleteMapping("/{email:.+}")
	public ResponseEntity<Void> deleteUser(@PathVariable("email") String email) {
		userService.delete(email);
		return ResponseEntity.ok().build();
	}
}
