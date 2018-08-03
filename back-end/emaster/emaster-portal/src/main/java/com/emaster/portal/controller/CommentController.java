package com.emaster.portal.controller;

import java.util.Optional;

import org.hibernate.validator.internal.metadata.aggregated.rule.VoidMethodsMustNotBeReturnValueConstrained;
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
import com.emaster.common.exception.PortalException;
import com.emaster.portal.service.CommentService;

@RestController
@RequestMapping("comments")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping
	public ResponseEntity<PageDto<CommentDto>> findAll(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws PortalException {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(commentService.findAll(page, size));

	}

	@PostMapping
	public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto) throws PortalException {
		return ResponseEntity.ok().body(commentService.create(commentDto));
	}

	@PutMapping
	public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto) throws PortalException {
		return ResponseEntity.ok().body(commentService.update(commentDto));
	}
	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDto> findOne(@PathVariable("commentId") String commentId) throws PortalException{
		return ResponseEntity.ok().body(commentService.findOne(commentId));
	}
	@DeleteMapping("/{commentId}")
	public ResponseEntity<CommentDto> delete(@PathVariable("commentId") String commentID) throws PortalException{
		commentService.delete(commentID);
		return ResponseEntity.ok().build();
	}

}
