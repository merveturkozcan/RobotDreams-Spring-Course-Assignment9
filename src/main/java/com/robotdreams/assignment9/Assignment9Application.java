package com.robotdreams.assignment9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Assignment9Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment9Application.class, args);
    }

}
