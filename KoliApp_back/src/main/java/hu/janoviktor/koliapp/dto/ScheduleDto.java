package hu.janoviktor.koliapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {

	private long scheduleId;
	private long userId;
	private long roomId;
	private String fromDate;
	private String toDate;
	
}
