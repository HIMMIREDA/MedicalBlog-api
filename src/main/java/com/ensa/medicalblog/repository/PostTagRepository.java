package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.PostTagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends MongoRepository<PostTagEntity,String> {
    List<PostTagEntity> findByTagId(String tagId);
}
