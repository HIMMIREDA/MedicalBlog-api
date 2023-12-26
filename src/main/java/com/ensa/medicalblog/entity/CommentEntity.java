package com.ensa.medicalblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "comment")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String content;
    private String postId;
    private String userId;
    private String username;
}
