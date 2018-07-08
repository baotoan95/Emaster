package com.emaster.portal.service;

import com.emaster.portal.dto.UserDto;

public interface UserSerivce {
	UserDto findByEmail(String email);
}
