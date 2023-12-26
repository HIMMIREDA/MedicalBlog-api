package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.LikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends MongoRepository<LikeEntity, String> {
    Optional<LikeEntity> findByPostIdAndUserId(String postId, String userId);
}
