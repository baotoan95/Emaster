package com.emaster.portal.dal;

import java.util.Optional;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;

public interface UserDAL {
	public PageDto<UserDto> getUsers(Optional<Integer> page, Optional<Integer> size) throws PortalException;
}
