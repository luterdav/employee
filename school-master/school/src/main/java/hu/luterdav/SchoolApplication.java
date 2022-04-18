package hu.luterdav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.luterdav.service.InitDBService;

@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

	@Autowired
	InitDBService initDBService;
	
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		initDBService.init();
	}

}
