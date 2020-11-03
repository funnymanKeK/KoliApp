package hu.elte.koliapp.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
	private User user;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String text;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
	private Room room;
    
    @ManyToMany
    @JoinTable
    private List<User> likes;

}
