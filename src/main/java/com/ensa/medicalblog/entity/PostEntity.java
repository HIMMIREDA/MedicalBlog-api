package com.ensa.medicalblog.entity;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "post")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    @Indexed
    private String title;


    private String image;

    private String content;

    @Field(targetType = FieldType.DATE_TIME)
    @CreatedDate
    private LocalDateTime createdAt;

    @Field(targetType = FieldType.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    @DBRef
    private UserEntity author;
    private Set<String> tags = new HashSet<>();

    private List<String> comments = new ArrayList<>();

    private Integer likes;

    @PrePersist
    public void prePersist() {
            this.likes = 0;
        }
}
