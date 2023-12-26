package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.PostTagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends MongoRepository<PostTagEntity,String> {
}
