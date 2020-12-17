package hu.elte.koliapp.repositories;

import org.springframework.data.repository.CrudRepository;

import hu.elte.koliapp.entities.Post;

public interface PostRepository extends CrudRepository<Post, Integer>{

}
