package hu.janoviktor.koliapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.dto.CommentDto;
import hu.janoviktor.koliapp.entity.Comment;
import hu.janoviktor.koliapp.mapper.CommentMapper;
import hu.janoviktor.koliapp.repository.CommentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;
	
	@Transactional
	public Comment save(CommentDto commentDto) {
		Comment comment = commentMapper.mapCommentDtoToComment(commentDto);
		return commentRepository.save(comment);
	}
	
}
