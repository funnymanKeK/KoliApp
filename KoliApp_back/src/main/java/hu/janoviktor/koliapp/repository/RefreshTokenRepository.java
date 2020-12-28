package hu.janoviktor.koliapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.janoviktor.koliapp.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);

	void deleteByToken(String token);
}
