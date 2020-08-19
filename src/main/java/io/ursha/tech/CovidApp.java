package io.ursha.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidApp {
    public static void main(String[] args){
        SpringApplication.run(CovidApp.class, args);
    }
}
