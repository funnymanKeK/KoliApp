package hu.janoviktor.koliapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import hu.janoviktor.koliapp.dto.PostCreateDto;
import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.dto.ScheduleDto;
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
			@Mapping(target = "numberOfLikes", expression = "java(getNumberOfLikes(post))"),
			@Mapping(target = "username", expression = "java(getUserName(post))"),
			@Mapping(target = "roomLevel", source = "post.room.level"),
			@Mapping(target = "roomNumber", source = "post.room.number") })
	public abstract PostDto mapPostToPostDto(Post post);

	@Mappings({ @Mapping(target = "comments", ignore = true), @Mapping(target = "likes", ignore = true),
			@Mapping(target = "postId", ignore = true),
			@Mapping(target = "room", expression = "java(getRoom(postCreateDto.getRoomId()))"),
			@Mapping(target = "user", expression = "java(getUserFromUserId(postCreateDto.getUserId()))"),
			@Mapping(target = "text", source = "content"),
			@Mapping(target = "archive", expression = "java(Boolean.FALSE)"), })
	public abstract Post mapPostCreateDtoToPost(PostCreateDto postCreateDto);

	@Mappings({ @Mapping(target = "postId", ignore = true),
			@Mapping(target = "comments", expression = "java(getEmptyComments())"),
			@Mapping(target = "likes", expression = "java(getEmptyLikes())"),
			@Mapping(target = "room", expression = "java(getRoom(scheduleDto.getRoomId()))"),
			@Mapping(target = "text", expression = "java(getText(scheduleDto))"),
			@Mapping(target = "title", expression = "java(getTitle(scheduleDto))"),
			@Mapping(target = "user", expression = "java(getUserFromUserId(scheduleDto.getUserId()))"),
			@Mapping(target = "archive", expression = "java(Boolean.FALSE)"), })
	public abstract Post mapScheduleDtoToPost(ScheduleDto scheduleDto);

	protected String getText(ScheduleDto scheduleDto) {
		return getRoom(scheduleDto.getRoomId()).getLevel() + " szinten lévő "
				+ getRoom(scheduleDto.getRoomId()).getNumber() + " számú szobát lefoglalta "
				+ getUserFromUserId(scheduleDto.getUserId()).getUsername() + "nevű felhasználó "
				+ scheduleDto.getFromDate().toString() + "-tól " + scheduleDto.getToDate() + "-ig.";
	}

	protected String getTitle(ScheduleDto scheduleDto) {
		return getRoom(scheduleDto.getRoomId()).getLevel() + " szinten lévő "
				+ getRoom(scheduleDto.getRoomId()).getNumber() + " számú szobát lefoglalták";
	}

	protected List<Comment> getEmptyComments() {
		return new ArrayList<Comment>();
	}

	protected List<User> getEmptyLikes() {
		return new ArrayList<User>();
	}

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

	protected User getUserFromUserId(long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new KoliAppException("No user found with user_id: " + userId));
		return user;
	}

	protected int getNumberOfLikes(Post post) {
		return post.getLikes().size();
	}

	protected String getUserName(Post post) {
		return post.getUser().getUsername();
	}

}
