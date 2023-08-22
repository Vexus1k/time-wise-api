package com.example.timewiseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.timewiseapi")
public class TimeWiseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeWiseApiApplication.class, args);
	}

}
