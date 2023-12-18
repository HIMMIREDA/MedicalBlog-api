package com.ensa.medicalblog.entity;

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
import java.util.UUID;

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
}
