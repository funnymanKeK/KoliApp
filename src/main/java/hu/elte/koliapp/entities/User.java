package hu.elte.koliapp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public enum Role {
		ROLE_GUEST, ROLE_USER, ROLE_ADMIN
	}

	@Column(nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Room room;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "user")
	private List<Schedule> schedules;
	
    @ManyToMany(mappedBy = "likes")
    @JsonIgnore
    private List<Post> likedPosts;
}