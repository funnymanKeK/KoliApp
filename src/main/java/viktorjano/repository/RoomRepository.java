package viktorjano.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viktorjano.entity.Room;

public class RoomRepository {
	public interface UserRepository extends CrudRepository<Room, Integer> {
		
	}
}
