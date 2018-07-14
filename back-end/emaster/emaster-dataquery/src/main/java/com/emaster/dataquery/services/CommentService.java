package com.emaster.dataquery.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emaster.dataquery.entities.Comment;
import com.emaster.dataquery.exception.NotFoundException;

public interface CommentService {
	Page<Comment> findAll(Pageable pageable);

	Comment findOne(String id);

	Comment create(Comment comment);

	Comment update(Comment comment) throws NotFoundException;

	void delete(String id);
}
