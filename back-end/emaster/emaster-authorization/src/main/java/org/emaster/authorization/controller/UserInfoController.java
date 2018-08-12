package org.emaster.authorization.controller;

import java.security.Principal;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
@RequestMapping("user")
public class UserInfoController {
	@GetMapping("/info")
	public Principal principal(Principal user) {
		return user;
	}
}
