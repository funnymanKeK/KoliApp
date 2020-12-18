package hu.elte.koliapp.repositories;

import org.springframework.data.repository.CrudRepository;

import hu.elte.koliapp.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
