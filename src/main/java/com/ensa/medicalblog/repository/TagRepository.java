package com.ensa.medicalblog.repository;

import com.ensa.medicalblog.entity.TagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<TagEntity, String> {
    Optional<TagEntity> findByTagName(String tagName);
}
