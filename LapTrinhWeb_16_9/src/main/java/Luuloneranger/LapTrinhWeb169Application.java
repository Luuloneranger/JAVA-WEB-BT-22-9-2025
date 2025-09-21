package Luuloneranger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"Luuloneranger.Controller"})
@ComponentScan
public class LapTrinhWeb169Application {

	public static void main(String[] args) {
		SpringApplication.run(LapTrinhWeb169Application.class, args);
	}

}
