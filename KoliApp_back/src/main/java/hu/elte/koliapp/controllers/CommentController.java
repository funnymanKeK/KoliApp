package hu.elte.koliapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.elte.koliapp.entities.Comment;
import hu.elte.koliapp.repositories.CommentRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("")
	public ResponseEntity<Iterable<Comment>> getAll() {
		return ResponseEntity.ok(commentRepository.findAll());
	}

	@PostMapping("")
	public ResponseEntity<Comment> insert(@RequestBody Comment comment) {
		return ResponseEntity.ok(commentRepository.save(comment));
	}

}
