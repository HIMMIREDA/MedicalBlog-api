package com.ensa.medicalblog.graphql.model;

import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {
    @GraphQLNonNull
    private String id;

    @GraphQLNonNull
    private String title;

    @GraphQLNonNull
    private String content;

    @GraphQLNonNull
    private LocalDateTime createdAt;

    private Set<String> tags;

    private List<String> comments;

    private Integer likes;
}
