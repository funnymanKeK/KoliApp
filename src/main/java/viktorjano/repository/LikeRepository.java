package viktorjano.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viktorjano.entity.Like;


public class LikeRepository {
	public interface UserRepository extends CrudRepository<Like, Integer> {
		
	}
}
