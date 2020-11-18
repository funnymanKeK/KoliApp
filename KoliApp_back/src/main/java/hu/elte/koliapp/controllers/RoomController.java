package hu.elte.koliapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.elte.koliapp.entities.Room;
import hu.elte.koliapp.repositories.RoomRepository;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;

	@GetMapping("/all")
	public ResponseEntity<Iterable<Room>> getAll() {
		return ResponseEntity.ok(roomRepository.findAll());
	}
	
}
