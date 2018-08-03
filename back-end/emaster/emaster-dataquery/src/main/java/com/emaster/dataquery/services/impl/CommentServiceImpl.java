package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Comment;
import com.emaster.dataquery.entities.User;
import com.emaster.dataquery.repositories.CommentRepository;
import com.emaster.dataquery.repositories.UserRepository;
import com.emaster.dataquery.services.CommentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<Comment> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder().message(MessageContant.INVALID_PARAM).dateTime(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST).build();
		}
		log.info("Start findAll(page={},size={})", pageNum, pageSize);
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Comment> pageComment = commentRepository.findAll(pageable);
		log.info("Finish findAll");
		return pageComment;
	}

	@Override
	public Comment findOne(String id) {
		log.info("Start findOne({})", id);
		Optional<Comment> result = commentRepository.findById(id);
		if (result.isPresent()) {
			log.info("Finish findOne");
			return result.get();
		}
		return null;
	}

	@Override
	public Comment create(Comment comment) throws DataQueryException {
		log.info("Start create");
		if (Objects.nonNull(comment)) {
			Optional<User> user = userRepository.findByEmail(comment.getCreatedBy().getEmail());
			if (user.isPresent()) {
				comment.setId(null);
				comment.setCreatedBy(user.get());

				comment.setCreatedDate(new Date());
				Comment createdComment = commentRepository.save(comment);
				log.info("Finish create");
				return createdComment;
			} else {
				throw DataQueryException.builder().message(DataQueryMessage.DONT_HAVE_PERMIT_CREATE)
						.dateTime(LocalDateTime.now()).status(HttpStatus.FORBIDDEN).build();
			}
		} else {
			throw DataQueryException.builder().message(MessageContant.INVALID_PARAM).dateTime(LocalDateTime.now())
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public Comment update(Comment comment) throws DataQueryException {
		log.info("Start update with id={}", comment.getId());
		Optional<Comment> existedComment = commentRepository.findById(comment.getId());
		if (existedComment.isPresent()) {
			Comment newComment = existedComment.get();
			newComment.setCreatedDate(existedComment.get().getCreatedDate());
			newComment.setCreatedBy(existedComment.get().getCreatedBy());
			newComment.setContent(comment.getContent());
			newComment.setParent(comment.getParent());
			newComment.setVoteDownCount(comment.getVoteDownCount());
			newComment.setVoteUpCount(comment.getVoteUpCount());
			Comment updatedComment = commentRepository.save(newComment);
			log.info("Finish update");
			return updatedComment;
		} else {
			throw DataQueryException.builder().message(DataQueryMessage.GIVEN_ID_NOT_EXISTED)
					.status(HttpStatus.BAD_REQUEST).dateTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public void delete(String id) {
		log.info("Start delete");
		commentRepository.deleteById(id);
		log.info("Finish delete");
	}

}
