package com.emaster.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.portal.dal.CommentDAL;
import com.emaster.portal.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAL commentDAL;

	@Override
	public PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		return commentDAL.findAll(page, size);
	}

	@Override
	public CommentDto create(CommentDto commentDto) throws PortalException {
		return commentDAL.create(commentDto);
	}

	@Override
	public CommentDto update(CommentDto commentDto) throws PortalException {
		return commentDAL.update(commentDto);
	}

	@Override
	public CommentDto findOne(String id) throws PortalException {
		return commentDAL.findOne(id);
	}

	@Override
	public void delete(String id) throws PortalException {
		commentDAL.delete(id);
	}
}
