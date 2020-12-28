package hu.janoviktor.koliapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.entity.Comment;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.entity.User;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

	@Mappings({ @Mapping(target = "id", ignore = true),
			@Mapping(target = "comments", expression = "java(getComments(post.getComments()))"),
			@Mapping(target = "creatorLiked", expression = "java(didUserLiked(post))"),
			@Mapping(target = "numberOfLikes", expression = "java(getNumberOfLikes(post))"),
			@Mapping(target = "username", expression = "java(getUserName(post))"),
			@Mapping(target = "roomLevel", source = "post.room.level"),
			@Mapping(target = "roomNumber", source = "post.room.number") })
	public abstract PostDto mapPostToPostDto(Post post);

	protected List<String> getComments(List<Comment> comments) {
		List<String> res = new ArrayList<String>();
		for (Comment comment : comments) {
			res.add(comment.getText());
		}
		return res;
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

	protected int getNumberOfLikes(Post post) {
		return post.getLikes().size();
	}

	protected String getUserName(Post post) {
		return post.getUser().getUsername();
	}

}
