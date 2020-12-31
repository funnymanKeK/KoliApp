package hu.janoviktor.koliapp.dto;

import java.time.Instant;

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
	private Instant fromDate;
	private Instant toDate;
	
}
