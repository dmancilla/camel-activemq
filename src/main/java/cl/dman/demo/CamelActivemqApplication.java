package cl.dman.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelActivemqApplication {

	public static void main(String[] args) {
        System.setProperty("hawtio.authenticationEnabled", "false");
		SpringApplication.run(CamelActivemqApplication.class, args);
	}

}
