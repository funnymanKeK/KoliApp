package hu.janoviktor.koliapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.janoviktor.koliapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
