package com.ensa.medicalblog.graphql.model;

import com.ensa.medicalblog.entity.CommentEntity;
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

    @GraphQLNonNull
    private Author author;

    @GraphQLNonNull
    private String image;
    private Set<String> tags;

    private List<CommentEntity> comments;

    private Integer likes;
    private Boolean isLiked;
}
