package com.emaster.portal.controller;

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
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageDto<UserDto>> getUsers(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws PortalException {
		return ResponseEntity.ok().body(userService.getUsers(page, size));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUsers(@RequestBody UserDto userDto) throws PortalException {
		return ResponseEntity.ok().body(userService.create(userDto));
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws PortalException {
		return ResponseEntity.ok().body(userService.update(userDto));
	}

	@DeleteMapping("/{email:.+}")
	public ResponseEntity<Void> deleteUser(@PathVariable("email") String email) throws PortalException {
		userService.delete(email);
		return ResponseEntity.ok().build();
	}
}
