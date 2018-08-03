package com.emaster.portal.service;

import java.util.List;
import java.util.Optional;

import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;

public interface CommentService {
	PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException;
	
	CommentDto create (CommentDto commentDto) throws PortalException;
	
	CommentDto update(CommentDto commentDto) throws PortalException;
	
	CommentDto findOne(String id) throws PortalException;
	
	void delete (String id) throws PortalException;
	
	List<CommentDto> findForASession(Optional<String> userId) throws PortalException;

}
