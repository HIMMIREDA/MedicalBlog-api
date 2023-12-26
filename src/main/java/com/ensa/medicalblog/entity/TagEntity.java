package com.ensa.medicalblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;

@Document(collection = "tag")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TagEntity {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String tagName;
}
