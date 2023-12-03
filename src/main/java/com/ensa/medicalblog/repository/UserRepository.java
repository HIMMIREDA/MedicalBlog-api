package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
