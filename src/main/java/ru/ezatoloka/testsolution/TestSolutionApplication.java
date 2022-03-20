package ru.ezatoloka.testsolution;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@EnableWebMvc
@EnableScheduling
@SpringBootApplication
public class TestSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSolutionApplication.class, args);
	}

	public JavaTimeModule javaTimeModule() {
		return new JavaTimeModule();
	}
}
