package viktorjano;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"viktorjano.controller"})
public class KoliAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoliAppApplication.class, args);
	}

}
