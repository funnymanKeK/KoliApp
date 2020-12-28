package hu.janoviktor.koliapp.service;

import java.time.Instant;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.dto.AuthenticationResponse;
import hu.janoviktor.koliapp.dto.LoginRequest;
import hu.janoviktor.koliapp.dto.RefreshTokenRequest;
import hu.janoviktor.koliapp.dto.RegisterRequest;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.exception.KoliAppException;
import hu.janoviktor.koliapp.repository.UserRepository;
import hu.janoviktor.koliapp.security.JwtProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final RefreshTokenService refreshTokenService;
	private final JwtProvider jwtProvider;

	@Transactional
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(true);
		userRepository.save(user);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtProvider.generateToken(authenticate);
		return AuthenticationResponse.builder().authenticationToken(token)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getExpInMilis())).username(loginRequest.getUsername())
				.id(this.requireByUsername(loginRequest.getUsername()).getUserId())
				.build();
	}

	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
		return AuthenticationResponse.builder().authenticationToken(token)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getExpInMilis()))
				.username(refreshTokenRequest.getUsername()).build();
	}
	
	private User requireByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new KoliAppException("No user found"));
		return user;
	}

}
