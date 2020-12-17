package hu.elte.koliapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.elte.koliapp.entities.Post;
import hu.elte.koliapp.repositories.PostRepository;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	@GetMapping("")
	public ResponseEntity<Iterable<Post>> getAll() {
		return ResponseEntity.ok(postRepository.findAll());
	}
	
}
