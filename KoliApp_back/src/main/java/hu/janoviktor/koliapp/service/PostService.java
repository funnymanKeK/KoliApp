package hu.janoviktor.koliapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.dto.LikeDto;
import hu.janoviktor.koliapp.dto.PostCreateDto;
import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.dto.ScheduleDto;
import hu.janoviktor.koliapp.dto.UserIdAndPostId;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.entity.User.Role;
import hu.janoviktor.koliapp.exception.KoliAppException;
import hu.janoviktor.koliapp.mapper.LikeMapper;
import hu.janoviktor.koliapp.mapper.PostMapper;
import hu.janoviktor.koliapp.repository.PostRepository;
import hu.janoviktor.koliapp.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final PostMapper postMapper;
	private final LikeMapper likeMapper;

	@Transactional(readOnly = true)
	public List<PostDto> getAll() {
		List<PostDto> all = new ArrayList<PostDto>();
		for(Post post : postRepository.findAll()) {
			all.add(postMapper.mapPostToPostDto(post));
		}
		all.removeIf(e -> e.isArchive());
		return all;
	}

	@Transactional
	public Post save(PostCreateDto postCreateDto) {
		return postRepository.save(postMapper.mapPostCreateDtoToPost(postCreateDto));
	}

	@Transactional
	public void save(LikeDto likeDto) {
		postRepository.save(likeMapper.mapLikeDtotoPost(likeDto));
	}

	@Transactional
	public Post save(ScheduleDto scheduleDto) {
		return postRepository.save(postMapper.mapScheduleDtoToPost(scheduleDto));
	}

	public boolean archive(UserIdAndPostId uap) {
		User user = userRepository.findById(uap.getUserId())
				.orElseThrow(() -> new KoliAppException("No user found with user_id: " + uap.getUserId()));
		if (user.getRole() == Role.ROLE_ADMIN) {
			Post post = postRepository.findById(uap.getPostId())
					.orElseThrow(() -> new KoliAppException("no post found"));
			post.setArchive(true);
			postRepository.save(post);
			return true;
		}
		return false;
	}

	public Boolean likeCheck(UserIdAndPostId uap) {
		User user = userRepository.findById(uap.getUserId())
				.orElseThrow(() -> new KoliAppException("No user found with user_id: " + uap.getUserId()));
		Post post = postRepository.findById(uap.getPostId())
				.orElseThrow(() -> new KoliAppException("No post found with user_id: " + uap.getUserId()));
		if(post.getLikes().contains(user)) {
			return true;
		}else {
			return false;
		}
	}

}
