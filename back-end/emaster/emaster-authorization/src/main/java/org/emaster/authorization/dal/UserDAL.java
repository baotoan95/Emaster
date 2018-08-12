package org.emaster.authorization.dal;

import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;

public interface UserDAL {
	UserDto findByUserEmail(String email) throws PortalException;
}
