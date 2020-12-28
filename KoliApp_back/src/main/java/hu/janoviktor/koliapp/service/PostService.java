package hu.janoviktor.koliapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.dto.PostCreateDto;
import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.mapper.PostMapper;
import hu.janoviktor.koliapp.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;

	@Transactional(readOnly = true)
	public List<PostDto> getAll() {
		return  postRepository.findAll().stream().map(postMapper::mapPostToPostDto).collect(Collectors.toList());
	}

	@Transactional
	public Post save(PostCreateDto postCreateDto) {
		return postRepository.save(postMapper.mapPostCreateDtoToPost(postCreateDto));
	}

}
