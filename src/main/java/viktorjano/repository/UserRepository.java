package viktorjano.repository;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import viktorjano.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}