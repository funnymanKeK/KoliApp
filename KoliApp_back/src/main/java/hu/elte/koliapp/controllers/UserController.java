package hu.elte.koliapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.elte.koliapp.entities.User;
import hu.elte.koliapp.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	/*
	@PostMapping("")
	public ResponseEntity<User> register(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole(User.Role.ROLE_USER);
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	*/

	@GetMapping("")
	public ResponseEntity<Iterable<User>> getAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

}
