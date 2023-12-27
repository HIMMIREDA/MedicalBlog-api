package com.ensa.medicalblog.mapper;

import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;

import java.util.ArrayList;

public class PostMapper {
    static public PostEntity toEntity(PostInput postInput) {
        return PostEntity.builder()
                .title(postInput.getTitle())
                .content(postInput.getContent())
                .tags(postInput.getTags())
                .likes(0)
                .comments(new ArrayList<>())
                .build();
    }

    static public Post toModel(PostEntity postEntity) {

        return Post.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .title(postEntity.getTitle())
                .createdAt(postEntity.getCreatedAt())
                .image(postEntity.getImage())
                .tags(postEntity.getTags())
                .comments(postEntity.getComments())
                .likes(postEntity.getLikes())
                .author(AuthorMapper.toModel(postEntity.getAuthor()))
                .build();
    }
}
