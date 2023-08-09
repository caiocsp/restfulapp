package b4a.challenge.restfulapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RestfulappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulappApplication.class, args);
	}

}
