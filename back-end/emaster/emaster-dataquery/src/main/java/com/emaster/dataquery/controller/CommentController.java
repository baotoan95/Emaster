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

import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Comment;
import com.emaster.dataquery.services.CommentService;

@RestController
@RequestMapping("comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public ResponseEntity<PageDto<Comment>> findAll(@RequestParam(value = "page", required = false) Optional<Integer> page, 
			@RequestParam(value = "size", required = false) Optional<Integer> size) throws DataQueryException {
		return ResponseEntity.ok().body(commentService.findAll(page, size));
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<Comment> findOne(@PathVariable("commentId") String commentId) {
		return ResponseEntity.ok().body(commentService.findOne(commentId));
	}

	@PostMapping
	public ResponseEntity<Comment> create(@RequestBody Comment comment) throws DataQueryException {
		return ResponseEntity.ok().body(commentService.create(comment));
	}

	@PutMapping
	public ResponseEntity<Comment> update(@RequestBody Comment comment) throws DataQueryException {
		return ResponseEntity.ok().body(commentService.update(comment));
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> delete(@PathVariable("commentId") String commentId) {
		commentService.delete(commentId);
		return ResponseEntity.ok().build();
	}
}
