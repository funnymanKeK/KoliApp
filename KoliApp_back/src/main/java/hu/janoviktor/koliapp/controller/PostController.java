package hu.janoviktor.koliapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.janoviktor.koliapp.dto.LikeDto;
import hu.janoviktor.koliapp.dto.PostCreateDto;
import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.dto.ScheduleDto;
import hu.janoviktor.koliapp.dto.UserIdAndPostId;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public ResponseEntity<List<PostDto>> getAll() {
		return ResponseEntity.ok(postService.getAll());
	}

	@PostMapping
	public ResponseEntity<Post> insert(@RequestBody PostCreateDto postCreateDto) {
		return ResponseEntity.ok(postService.save(postCreateDto));
	}

	@PostMapping("/schedule")
	public ResponseEntity<Post> insert(@RequestBody ScheduleDto scheduleDto) {
		return ResponseEntity.ok(postService.save(scheduleDto));
	}

	@PostMapping("/like")
	public ResponseEntity<Boolean> like(@RequestBody LikeDto likeDto) {
		return ResponseEntity.ok(postService.save(likeDto));
	}

	@PostMapping("/archive")
	public ResponseEntity<Boolean> delete(@RequestBody UserIdAndPostId uap) {
		return ResponseEntity.ok(postService.archive(uap));
	}
	
	@PostMapping("/liked")
	public ResponseEntity<Boolean> likeCheck(@RequestBody UserIdAndPostId uap) {
		return ResponseEntity.ok(postService.likeCheck(uap));
	}
}
