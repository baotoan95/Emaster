package com.emaster.dataquery.facade.impl;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Comment;
import com.emaster.dataquery.facade.CommentFacade;
import com.emaster.dataquery.services.CommentService;
import com.google.common.reflect.TypeToken;

@Component
public class CommentFacadeImpl implements CommentFacade {
	private CommentService commentService;
	private ModelMapper modelMapper;

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@SuppressWarnings("serial")
	@Override
	public PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException {
		Type pageDtoType = new TypeToken<PageDto<CommentDto>>() {
		}.getType();
		Page<Comment> pageComment = commentService.findAll(page, size);
		PageDto<CommentDto> pageDto = modelMapper.map(pageComment, pageDtoType);
		pageDto.setContent(pageComment.stream().map(comment -> {
			return modelMapper.map(comment, CommentDto.class);
		}).collect(Collectors.toList()));

		return pageDto;
	}

	@Override
	public CommentDto findOne(String id) {
		Comment comment = commentService.findOne(id);
		if(Objects.nonNull(comment)) {
			return modelMapper.map(comment, CommentDto.class);
		}
		return null;
	}

	@Override
	public CommentDto create(CommentDto commentDto) throws DataQueryException {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		return modelMapper.map(commentService.create(comment), CommentDto.class);
	}

	@Override
	public CommentDto update(CommentDto commentDto) throws DataQueryException {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		
		return modelMapper.map(commentService.update(comment), CommentDto.class);
	}

	@Override
	public void delete(String id) {
		commentService.delete(id);
	}

}
