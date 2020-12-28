package hu.janoviktor.koliapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import hu.janoviktor.koliapp.dto.CommentDto;
import hu.janoviktor.koliapp.entity.Comment;
import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.exception.KoliAppException;
import hu.janoviktor.koliapp.repository.PostRepository;
import hu.janoviktor.koliapp.repository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	@Mappings({ @Mapping(target = "id", ignore = true),
			@Mapping(target = "user", expression = "java(getUserFromUserId(commentdto.getUserId()))"),
			@Mapping(target = "post", expression = "java(getCommentFromCommentId(commentdto.getPostId()))") })
	public abstract Comment mapCommentDtoToComment(CommentDto commentdto);

	protected Post getCommentFromCommentId(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new KoliAppException("No comment found"));
		return post;
	}

	protected User getUserFromUserId(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new KoliAppException("No user found"));
		return user;
	}

}
