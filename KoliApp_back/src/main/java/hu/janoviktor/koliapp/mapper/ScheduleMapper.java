package hu.janoviktor.koliapp.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import hu.janoviktor.koliapp.dto.ScheduleDto;
import hu.janoviktor.koliapp.entity.Room;
import hu.janoviktor.koliapp.entity.Schedule;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.exception.KoliAppException;
import hu.janoviktor.koliapp.repository.RoomRepository;
import hu.janoviktor.koliapp.repository.UserRepository;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Mappings({ @Mapping(target = "id", ignore = true),
			@Mapping(target = "user", expression = "java(getUserFromUserId(scheduleDto.getUserId()))"),
			@Mapping(target = "room", expression = "java(getRoomFromUserId(scheduleDto.getRoomId()))"),
			@Mapping(target = "fromDate", expression = "java(stringToDate(scheduleDto.getFromDate()))"),
			@Mapping(target = "toDate", expression = "java(stringToDate(scheduleDto.getToDate()))"), })
	public abstract Schedule mapScheduleDtoToSchedule(ScheduleDto scheduleDto);

	protected User getUserFromUserId(long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new KoliAppException("No user found"));
		return user;
	}

	protected Room getRoomFromUserId(long roomId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new KoliAppException("No room found"));
		return room;
	}

	protected Date stringToDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
		try {
			Date date = formatter.parse(str);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
}
