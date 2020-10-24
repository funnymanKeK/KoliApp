package viktorjano.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import viktorjano.entity.Schedule;

public class ScheduleRepository {

	public interface UserRepository extends CrudRepository<Schedule, Integer> {
		
	}
}
