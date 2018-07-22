package com.emaster.dataquery.services;

import java.util.Optional;

import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;

public interface CommentService {
	PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	CommentDto findOne(String id);

	CommentDto create(CommentDto comment) throws DataQueryException;

	CommentDto update(CommentDto comment) throws DataQueryException;

	void delete(String id);
}
