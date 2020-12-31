package hu.janoviktor.koliapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.janoviktor.koliapp.dto.ScheduleDto;
import hu.janoviktor.koliapp.entity.Schedule;
import hu.janoviktor.koliapp.service.ScheduleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/schedule")
@AllArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;
	
	@PostMapping
	public ResponseEntity<Schedule> insert(@RequestBody ScheduleDto scheduleDto){
		return ResponseEntity.ok(scheduleService.insert(scheduleDto));
	}
	
}
