package hu.janoviktor.koliapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.janoviktor.koliapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}