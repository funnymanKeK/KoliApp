package hu.janoviktor.koliapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public ResponseEntity<List<Post>> getAll() {
		return ResponseEntity.ok(postService.getAll());
	}

	@PostMapping
	public ResponseEntity<Post> insert(@RequestBody Post post) {
		return ResponseEntity.ok(postService.save(post));
	}
}
