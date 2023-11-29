package com.ensa.medicalblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class MedicalBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalBlogApplication.class, args);
    }

}
