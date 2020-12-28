package hu.janoviktor.koliapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	@Transactional
	public Post save(Post post) {
		return postRepository.save(post);
	}

}
