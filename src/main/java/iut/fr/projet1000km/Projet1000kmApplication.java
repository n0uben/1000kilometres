package iut.fr.projet1000km;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Projet1000kmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Projet1000kmApplication.class, args);
	}

}
