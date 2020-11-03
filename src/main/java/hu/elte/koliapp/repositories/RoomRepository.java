package hu.elte.koliapp.repositories;

import org.springframework.data.repository.CrudRepository;

import hu.elte.koliapp.entities.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {

}
