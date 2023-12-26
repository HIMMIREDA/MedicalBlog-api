package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.LikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends MongoRepository<LikeEntity, String> {
    LikeEntity findByPostIdAndUserId(String postId, String userId);
}
