package com.emaster.dataquery.services.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.common.validator.PaginationValidator;
import com.emaster.dataquery.constant.DataQueryMessage;
import com.emaster.dataquery.entities.Comment;
import com.emaster.dataquery.repositories.CommentRepository;
import com.emaster.dataquery.services.CommentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public PageDto<Comment> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		int pageNum = page.orElse(0);
		int pageSize = size.orElse(Integer.MAX_VALUE);
		if (!PaginationValidator.validate(pageNum, pageSize)) {
			throw DataQueryException.builder()
				.message(MessageContant.INVALID_PARAM)
				.dateTime(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST).build();
		}
		log.debug("Start findAll(page={},size={})", pageNum, pageSize);
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		PageDto<Comment> pageDto = new PageDto<Comment>()
				.build(commentRepository.findAll(pageable));
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public Comment findOne(String id) {
		log.debug("Start findOne({})", id);
		Optional<Comment> result = commentRepository.findById(id);
		log.debug("Finish findOne");
		return result.orElse(null);
	}

	@Override
	public Comment create(Comment comment) throws DataQueryException {
		log.debug("Start create");
		if(Objects.isNull(comment)) {
			throw DataQueryException.builder()
			.message(MessageContant.INVALID_PARAM)
			.dateTime(LocalDateTime.now())
			.status(HttpStatus.BAD_REQUEST).build();
		}
		comment.setId(null);
		Comment createdComment = commentRepository.save(comment);
		log.debug("Finish create");
		return createdComment;
	}

	@Override
	public Comment update(Comment comment) throws DataQueryException {
		log.debug("Start update with id={}", comment.getId());
		if (Objects.nonNull(comment) && commentRepository.existsById(comment.getId())) {
			Comment updatedComment = commentRepository.save(comment);
			log.debug("Finish update");
			return updatedComment;
		} else {
			throw DataQueryException.builder()
			.message(DataQueryMessage.GIVEN_ID_NOT_EXISTED)
			.status(HttpStatus.BAD_REQUEST)
			.dateTime(LocalDateTime.now()).build();
		}
	}

	@Override
	public void delete(String id) {
		log.debug("Start delete");
		commentRepository.deleteById(id);
		log.debug("Finish delete");
	}

}
