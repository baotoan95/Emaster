package com.emaster.dataquery.services;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Comment;

public interface CommentService {
	Page<Comment> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	Comment findOne(String id);

	Comment create(Comment comment) throws DataQueryException;

	Comment update(Comment comment) throws DataQueryException;

	void delete(String id);
}
