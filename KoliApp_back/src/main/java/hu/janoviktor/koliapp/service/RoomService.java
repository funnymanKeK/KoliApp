package hu.janoviktor.koliapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.janoviktor.koliapp.entity.Post;
import hu.janoviktor.koliapp.entity.Room;
import hu.janoviktor.koliapp.entity.Schedule;
import hu.janoviktor.koliapp.entity.User;
import hu.janoviktor.koliapp.repository.RoomRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	
	@Transactional(readOnly = true)
	public List<Room> getAll(){
		List<Room> res = new ArrayList<Room>();
		for(Room room : roomRepository.findAll()) {
			room.setUsers(new ArrayList<User>());
			room.setSchedules(new ArrayList<Schedule>());
			room.setPosts(new ArrayList<Post>());
			res.add(room);
		}
		return res;
	}
	
}
