package hu.janoviktor.koliapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.dto.ScheduleDto;
import hu.janoviktor.koliapp.entity.Schedule;
import hu.janoviktor.koliapp.mapper.ScheduleMapper;
import hu.janoviktor.koliapp.repository.ScheduleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final ScheduleMapper scheduleMapper;
	
	@Transactional
	public Schedule insert(ScheduleDto scheduleDto) {
		return scheduleRepository.save(scheduleMapper.mapScheduleDtoToSchedule(scheduleDto));
	}
	
}
