package hu.janoviktor.koliapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.janoviktor.koliapp.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
