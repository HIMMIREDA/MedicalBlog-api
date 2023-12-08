package com.ensa.medicalblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

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



    private String content;


    @Field(targetType = FieldType.DATE_TIME)
    @CreatedDate
    private LocalDateTime createdAt;

    @Field(targetType = FieldType.DATE_TIME)
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
