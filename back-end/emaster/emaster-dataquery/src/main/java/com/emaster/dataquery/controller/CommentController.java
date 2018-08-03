package com.emaster.dataquery.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.facade.CommentFacade;

@RestController
@RequestMapping("comments")
public class CommentController {
	private CommentFacade commentFacade;

	@Autowired
	public void setCommentFacade(CommentFacade commentFacade) {
		this.commentFacade = commentFacade;
	}

	@GetMapping
	public ResponseEntity<PageDto<CommentDto>> findAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(commentFacade.findAll(page, size));
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDto> findOne(@PathVariable("commentId") String commentId) {
		return ResponseEntity.ok().body(commentFacade.findOne(commentId));
	}

	@PostMapping
	public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) throws DataQueryException {
		return ResponseEntity.ok().body(commentFacade.create(commentDto));
	}

	@PutMapping
	public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto) throws DataQueryException {
		return ResponseEntity.ok().body(commentFacade.update(commentDto));
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> delete(@PathVariable("commentId") String commentId) {
		commentFacade.delete(commentId);
		return ResponseEntity.ok().build();
	}
}
