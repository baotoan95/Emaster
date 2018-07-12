package com.emaster.dataquery.services.impl;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Comment;
import com.emaster.dataquery.exception.NotFoundException;
import com.emaster.dataquery.repositories.CommentRepository;
import com.emaster.dataquery.services.CommentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Page<Comment> findAll(Pageable pageable) {
		log.debug("Start findAll (page={},size={})", pageable.getPageNumber(), pageable.getPageSize());
		Page<Comment> commentPage = commentRepository.findAll(pageable);
		log.debug("Finish findAll");
		return commentPage;
	}

	@Override
	public Comment findOne(ObjectId id) {
		log.debug("Start findOne({})", id);
		Optional<Comment> result = commentRepository.findById(id);
		log.debug("Finish findOne");
		return result.orElse(null);
	}

	@Override
	public Comment create(Comment comment) {
		log.debug("Start create");
		comment.setId(null);
		Comment createdComment = commentRepository.save(comment);
		log.debug("Finish create");
		return createdComment;
	}

	@Override
	public Comment update(Comment comment) throws NotFoundException {
		log.debug("Start update with id={}", comment.getId());
		if (commentRepository.existsById(comment.getId())) {
			Comment updatedComment = commentRepository.save(comment);
			log.debug("Finish update");
			return updatedComment;
		} else {
			throw new NotFoundException(DataQueryMessage.GIVEN_ID_NOT_EXISTED);
		}
	}

	@Override
	public void delete(ObjectId id) {
		log.debug("Start delete");
		commentRepository.deleteById(id);
		log.debug("Finish delete");
	}

}
