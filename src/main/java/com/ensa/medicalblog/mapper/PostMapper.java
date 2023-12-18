package com.ensa.medicalblog.mapper;

import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;

public class PostMapper {
    static public PostEntity toEntity(PostInput postInput) {
        return PostEntity.builder()
                .title(postInput.getTitle())
                .content(postInput.getContent())
                .build();
    }

    static public Post toModel(PostEntity postEntity) {

        return Post.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .title(postEntity.getTitle())
                .createdAt(postEntity.getCreatedAt())
                .image(postEntity.getImage())
                .author(AuthorMapper.toModel(postEntity.getAuthor()))
                .build();
    }
}