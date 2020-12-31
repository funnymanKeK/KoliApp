package hu.janoviktor.koliapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.janoviktor.koliapp.entity.Schedule;

public interface ScheduleRepository extends JpaRepository <Schedule, Long>{

}
