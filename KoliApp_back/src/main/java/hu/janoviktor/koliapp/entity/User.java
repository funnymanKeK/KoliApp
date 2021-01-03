package hu.janoviktor.koliapp.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long userId;

	@NotBlank(message = "Username is required")
	private String username;

	@NotBlank(message = "Password is required")
	private String password;

	private Instant created;

	private boolean enabled;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Room room;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@OneToMany(mappedBy = "user")
	private List<Schedule> schedules;

	@ManyToMany(mappedBy = "likes")
	@JsonIgnore
	private List<Post> likedPosts;

	@NotBlank(message = "Role must not be empty")
	@Enumerated(EnumType.STRING)
	private Role role;

	public enum Role {
		ROLE_USER, ROLE_ADMIN
	}
}
