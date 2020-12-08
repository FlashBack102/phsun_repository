package com.example.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootStartApplication {

	@Autowired
	BoardService service;

	public static void main(String[] args) {
		SpringApplication.run(BootStartApplication.class, args);
	}

	@RequestMapping("home")
	public String home() {
		service.home();
		return "home";
	}

}
