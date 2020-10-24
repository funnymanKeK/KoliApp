package viktorjano.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viktorjano.entity.Post;

public class PostEntity {
	public interface UserRepository extends CrudRepository<Post, Integer> {
		
	}
}
