package hu.janoviktor.koliapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
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

	public Boolean check(ScheduleDto scheduleDto) {
		Boolean isGood = true;
		ArrayList<Schedule> schedules = (ArrayList<Schedule>) scheduleRepository.findAll();
		for (Schedule schedule : schedules) {
			if (scheduleDto.getRoomId() == schedule.getRoom().getId()) {
				if (DateUtils.isSameDay(stringToDate(scheduleDto.getFromDate()), schedule.getFromDate())) {
					Date startDate1 = schedule.getFromDate();
					Date endDate1 = schedule.getToDate();
					Date startDate2 = stringToDate(scheduleDto.getFromDate());
					Date endDate2 = stringToDate(scheduleDto.getToDate());
					if (startDate1.before(endDate2) && endDate1.after(startDate2)) {
						isGood = false;
					}
				}
			}
		}
		return isGood;
	}

	private Date stringToDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
		try {
			Date date = formatter.parse(str);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

}
