package hu.elte.koliapp.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import hu.elte.koliapp.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
