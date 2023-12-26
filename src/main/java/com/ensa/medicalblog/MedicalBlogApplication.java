package com.ensa.medicalblog;

import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.entity.PostTagEntity;
import com.ensa.medicalblog.entity.TagEntity;
import com.ensa.medicalblog.entity.UserEntity;
import com.ensa.medicalblog.repository.PostRepository;
import com.ensa.medicalblog.repository.PostTagRepository;
import com.ensa.medicalblog.repository.TagRepository;
import com.ensa.medicalblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
@EnableMongoAuditing
public class MedicalBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalBlogApplication.class, args);

    }
}
