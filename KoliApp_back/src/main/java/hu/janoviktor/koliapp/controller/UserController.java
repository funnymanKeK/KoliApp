package hu.janoviktor.koliapp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.janoviktor.koliapp.dto.AuthenticationResponse;
import hu.janoviktor.koliapp.dto.LoginRequest;
import hu.janoviktor.koliapp.dto.NewPasswordDto;
import hu.janoviktor.koliapp.dto.RefreshTokenRequest;
import hu.janoviktor.koliapp.dto.RegisterRequest;
import hu.janoviktor.koliapp.service.RefreshTokenService;
import hu.janoviktor.koliapp.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	private final RefreshTokenService refreshTokenService;

	@PostMapping("/signup")

	public ResponseEntity<Boolean> signup(@RequestBody RegisterRequest registerRequest) {
		return  ResponseEntity.ok(userService.signup(registerRequest));
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest);
	}

	@PostMapping("refresh/token")
	public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		return userService.refreshToken(refreshTokenRequest);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!");
	}

	@PostMapping("/password/change")
	public ResponseEntity<Boolean> changePassword(@RequestBody NewPasswordDto newPasswordDto) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(newPasswordDto));
	}

}
