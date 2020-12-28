package hu.janoviktor.koliapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.janoviktor.koliapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	User findByUserId(Long userId);
}
