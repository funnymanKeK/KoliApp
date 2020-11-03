package hu.elte.koliapp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.scheduling.annotation.Schedules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int levels;

	@Column(nullable = false)
	private int numbers;

	@OneToMany(mappedBy = "room")
	private List<User> users;
	
	@OneToMany(mappedBy = "room")
	private List<Post> posts;
	
	@OneToMany(mappedBy = "room")
	private List<Schedule> schedules;

}