package hu.janoviktor.koliapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.janoviktor.koliapp.dto.CommentDto;
import hu.janoviktor.koliapp.entity.Comment;
import hu.janoviktor.koliapp.service.CommentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<Comment> insert(@RequestBody CommentDto commentDto) {
		return ResponseEntity.ok(commentService.save(commentDto));
	}
}
