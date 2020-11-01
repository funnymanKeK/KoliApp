package viktorjano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import viktorjano.entity.Room;
import viktorjano.repository.RoomRepository;

@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomRepository roomRepositry;
	
	@GetMapping("")
	public ResponseEntity<Iterable<Room>> getAll(){
		return ResponseEntity.ok(roomRepositry.findAll());
	}
	
}
