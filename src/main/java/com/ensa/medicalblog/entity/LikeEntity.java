package com.ensa.medicalblog.entity;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "like")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LikeEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String postId;
    private String userId;
    private Boolean liked;

    @PrePersist
    public void prePersist() {
        this.liked = false;
    }
}
