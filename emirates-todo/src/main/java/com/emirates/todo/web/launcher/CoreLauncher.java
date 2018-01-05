package com.emirates.todo.web.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.emirates.todo.web.*")
public class CoreLauncher {

	public static void main(String[] args) {
		SpringApplication.run(CoreLauncher.class, args);
	}
}

