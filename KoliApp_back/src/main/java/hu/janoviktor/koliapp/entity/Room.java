package hu.janoviktor.koliapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "level is required")
	private int level;

	@NotBlank(message = "message is required")
	private int number;

	@OneToMany(mappedBy = "room")
	private List<User> users;
	
    @JsonIgnore
	@OneToMany(mappedBy = "room")
	private List<Post> posts;
	
	@OneToMany(mappedBy = "room")
	private List<Schedule> schedules;
	
}
