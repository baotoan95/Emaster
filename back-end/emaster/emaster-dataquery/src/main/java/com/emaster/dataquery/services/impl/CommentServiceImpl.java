package com.emaster.dataquery.services.impl;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.MessageContant;
import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
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
	@Autowired
	private ModelMapper modelMappper;

	@Override
	public PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
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
		Page<Comment> pageComment = commentRepository.findAll(pageable);
		Type pageCommentDtoType = new TypeToken<PageDto<CommentDto>>() {}.getClass();
		PageDto<CommentDto> pageDto = modelMappper.map(pageComment, pageCommentDtoType);
		log.debug("Finish findAll");
		return pageDto;
	}

	@Override
	public CommentDto findOne(String id) {
		log.debug("Start findOne({})", id);
		Optional<Comment> result = commentRepository.findById(id);
		if(result.isPresent()) {
			log.debug("Finish findOne");
			return modelMappper.map(result.get(), CommentDto.class);
		}
		return null;
	}

	@Override
	public CommentDto create(CommentDto commentDto) throws DataQueryException {
		log.debug("Start create");
		if(Objects.isNull(commentDto)) {
			Optional<User> user = userRepository.findByEmail(commentDto.getCreatedBy());
			if(user.isPresent()) {
				commentDto.setId(null);
				commentDto.setCreatedDate(new Date());
				Comment newComment = modelMappper.map(commentDto, Comment.class);
				Comment createdComment = commentRepository.save(newComment);
				CommentDto createdCommentDto = modelMappper.map(createdComment, CommentDto.class);
				log.debug("Finish create");
				return createdCommentDto;
			} else {
				throw DataQueryException.builder()
				.message(DataQueryMessage.DONT_HAVE_PERMIT_CREATE)
				.dateTime(LocalDateTime.now())
				.status(HttpStatus.FORBIDDEN).build();
			}
		} else {
			throw DataQueryException.builder()
			.message(MessageContant.INVALID_PARAM)
			.dateTime(LocalDateTime.now())
			.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Override
	public CommentDto update(CommentDto commentDto) throws DataQueryException {
		log.debug("Start update with id={}", commentDto.getId());
		Optional<Comment> comment = commentRepository.findById(commentDto.getId());
		if (comment.isPresent()) {
			commentDto.setCreatedDate(comment.get().getCreatedDate());
			commentDto.setCreatedBy(comment.get().getCreatedBy());
			Comment newComment = modelMappper.map(commentDto, Comment.class);
			Comment updatedComment = commentRepository.save(newComment);
			CommentDto updatedCommentDto = modelMappper.map(updatedComment, CommentDto.class);
			log.debug("Finish update");
			return updatedCommentDto;
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
