package hu.janoviktor.koliapp;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.janoviktor.koliapp.dto.PostDto;
import hu.janoviktor.koliapp.dto.ScheduleDto;
import hu.janoviktor.koliapp.entity.Room;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

	  @LocalServerPort
	  private int port;
	
	  @Autowired
	  private TestRestTemplate restTemplate;
	  
	  @Test
	  public void shouldReturnAllPosts() throws Exception {
	    ResponseEntity<List<PostDto>> response = restTemplate.exchange("http://localhost:" + port + "/post", HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDto>>() {});
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertThat(response.getBody().size()).isEqualTo(1);
	  }
	  
	  @Test
	  public void shouldReturnAllRooms() throws Exception {
	    ResponseEntity<List<Room>> response = restTemplate.exchange("http://localhost:" + port + "/room", HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {});
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertThat(response.getBody().size()).isEqualTo(1);
	  }
	  
	  @Test
	  public void shouldReturnTrue() throws Exception {
		  
		 ScheduleDto scheduleDto = new ScheduleDto();
		 scheduleDto.setUserId(1);
		 scheduleDto.setRoomId(1);
		 scheduleDto.setFromDate("1996/06/06-9:30");
		 scheduleDto.setToDate("1996/06/06-10:30");
		  
	    ResponseEntity<Boolean> response = restTemplate.postForEntity("http://localhost:" + port + "/schedule/check", scheduleDto, Boolean.class );
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertThat(response.getBody().booleanValue()).isEqualTo(true);
	  }
	  
	  @Test
	  public void shouldReturnFalse() throws Exception {
		  
		 ScheduleDto scheduleDto = new ScheduleDto();
		 scheduleDto.setUserId(1);
		 scheduleDto.setRoomId(1);
		 scheduleDto.setFromDate("1997/06/06-11:30");
		 scheduleDto.setToDate("1997/06/06-13:30");
		  
	    ResponseEntity<Boolean> response = restTemplate.postForEntity("http://localhost:" + port + "/schedule/check", scheduleDto, Boolean.class );
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertThat(response.getBody().booleanValue()).isEqualTo(false);
	  }
}
