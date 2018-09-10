package com.emaster.portal.dal;

import java.util.List;

import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.PortalException;

public interface UserMemoryDAL {
	List<UserMemoryDto> findMissing(String userId, String categoryId, int lessThanOrEqual) throws PortalException;
	UserMemoryDto addToMemory(String userId, String statementId) throws PortalException;
}
