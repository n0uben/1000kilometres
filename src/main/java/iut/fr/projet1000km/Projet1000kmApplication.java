package iut.fr.projet1000km;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication()
@CrossOrigin(origins = "http://localhost:5173")
public class Projet1000kmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet1000kmApplication.class, args);
	}

}
