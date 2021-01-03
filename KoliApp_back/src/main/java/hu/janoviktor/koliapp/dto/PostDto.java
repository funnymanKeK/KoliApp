package hu.janoviktor.koliapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

	private long id;
	private String username;
	private String title;
	private String text;
	private List<String> comments;
	private String roomLevel;
	private String roomNumber;
	private int numberOfLikes;
	private boolean creatorLiked;
	
	
}
