package viktorjano.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viktorjano.entity.Comment;

public class CommentRepository {
	public interface UserRepository extends CrudRepository<Comment, Integer> {
		
	}
}
