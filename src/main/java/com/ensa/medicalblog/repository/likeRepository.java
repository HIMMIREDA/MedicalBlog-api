package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.LikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface likeRepository extends MongoRepository<LikeEntity, String> {
}
