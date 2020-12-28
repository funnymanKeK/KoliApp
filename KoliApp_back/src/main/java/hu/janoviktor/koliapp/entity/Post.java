package hu.janoviktor.koliapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Builder
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;

	@NotBlank(message = "title is required")
	private String title;

	@NotBlank(message = "text is required")
	private String text;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn
	private Room room;
	
	@ManyToOne
    @JoinColumn
	private User user;

	@ManyToMany
	@JoinTable
	private List<User> likes;
}
