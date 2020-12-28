package hu.janoviktor.koliapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.janoviktor.koliapp.entity.Room;
import hu.janoviktor.koliapp.service.RoomService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

	private final RoomService roomService;	
	
	@GetMapping
	public ResponseEntity<List<Room>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(roomService.getAll());
	}
}
