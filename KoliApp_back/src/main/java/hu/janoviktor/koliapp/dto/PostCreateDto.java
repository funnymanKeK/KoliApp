package hu.janoviktor.koliapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateDto {

	private long roomId;
	private long userId;
	private String title;
	private String content;
}
