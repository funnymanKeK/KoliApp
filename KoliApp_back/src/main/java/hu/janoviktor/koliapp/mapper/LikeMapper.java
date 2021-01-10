package hu.janoviktor.koliapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import hu.janoviktor.koliapp.dto.LikeDto;
import hu.janoviktor.koliapp.entity.Comment;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.entity.Room;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.exception.KoliAppException;
import hu.janoviktor.koliapp.repository.PostRepository;
import hu.janoviktor.koliapp.repository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class LikeMapper {

	@Autowired
	private PostRepository postRespotiry;
	@Autowired
	private UserRepository userRepository;

	@Mappings({ @Mapping(target = "archive", ignore = true),
			@Mapping(target = "comments", expression = "java(getCommentsById(likeDto.getPostId()))"),
			@Mapping(target = "likes", expression = "java(getLikesById(likeDto))"),
			@Mapping(target = "room", expression = "java(getRoomById(likeDto.getPostId()))"),
			@Mapping(target = "title", expression = "java(getTitleById(likeDto.getPostId()))"),
			@Mapping(target = "text", expression = "java(getTextById(likeDto.getPostId()))"),
			@Mapping(target = "user", expression = "java(getUserById(likeDto.getUserId()))"), })
	public abstract Post mapLikeDtotoPost(LikeDto likeDto);

	protected List<Comment> getCommentsById(long id) {
		Post post = postRespotiry.findById(id).orElseThrow(() -> new KoliAppException("no post found"));
		return post.getComments();
	}

	protected List<User> getLikesById(LikeDto likeDto) {
		Post post = postRespotiry.findById(likeDto.getPostId())
				.orElseThrow(() -> new KoliAppException("no post found"));
		List<User> likes = post.getLikes();
		if (likes.contains(getUserById(likeDto.getUserId()))) {
			likes.remove(getUserById(likeDto.getUserId()));
		} else {
			likes.add(getUserById(post.getUser().getUserId()));
		}
		return likes;
	}

	protected Room getRoomById(long id) {
		Post post = postRespotiry.findById(id).orElseThrow(() -> new KoliAppException("no post found"));
		return post.getRoom();
	}

	protected String getTitleById(long id) {
		Post post = postRespotiry.findById(id).orElseThrow(() -> new KoliAppException("no post found"));
		return post.getTitle();
	}

	protected String getTextById(long id) {
		Post post = postRespotiry.findById(id).orElseThrow(() -> new KoliAppException("no post found"));
		return post.getText();
	}

	protected User getUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new KoliAppException("no user found"));
		return user;
	}
}
