package hu.janoviktor.koliapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import hu.janoviktor.koliapp.dto.PostCreateDto;
import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.entity.Comment;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.entity.Room;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.exception.KoliAppException;
import hu.janoviktor.koliapp.repository.RoomRepository;
import hu.janoviktor.koliapp.repository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;

	@Mappings({ @Mapping(target = "id", source = "post.postId"),
			@Mapping(target = "comments", expression = "java(getComments(post.getComments()))"),
			@Mapping(target = "creatorLiked", expression = "java(didUserLiked(post))"),
			@Mapping(target = "numberOfLikes", expression = "java(getNumberOfLikes(post))"),
			@Mapping(target = "username", expression = "java(getUserName(post))"),
			@Mapping(target = "roomLevel", source = "post.room.level"),
			@Mapping(target = "roomNumber", source = "post.room.number") })
	public abstract PostDto mapPostToPostDto(Post post);

	@Mappings({ @Mapping(target = "comments", ignore = true), @Mapping(target = "likes", ignore = true),
			@Mapping(target = "postId", ignore = true),
			@Mapping(target = "room", expression = "java(getRoom(postCreateDto.getRoomId()))"),
			@Mapping(target = "user", expression = "java(getUserFromUserId(postCreateDto.getUserId()))"),
			@Mapping(target = "text", source = "content"), })
	public abstract Post mapPostCreateDtoToPost(PostCreateDto postCreateDto);

	protected List<String> getComments(List<Comment> comments) {
		List<String> res = new ArrayList<String>();
		for (Comment comment : comments) {
			res.add(comment.getText());
		}
		return res;
	}

	protected Room getRoom(long id) {
		Room room = roomRepository.findById(id).orElseThrow(() -> new KoliAppException("No room found"));
		return room;
	}

	protected boolean didUserLiked(Post post) {
		boolean res = false;
		for (User like : post.getLikes()) {
			if (like.getUserId() == post.getUser().getUserId()) {
				res = true;
			}
		}
		return res;
	}

	protected User getUserFromUserId(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new KoliAppException("No user found"));
		return user;
	}

	protected int getNumberOfLikes(Post post) {
		return post.getLikes().size();
	}

	protected String getUserName(Post post) {
		return post.getUser().getUsername();
	}

}
