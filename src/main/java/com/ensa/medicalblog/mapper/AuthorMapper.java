package com.ensa.medicalblog.mapper;

import com.ensa.medicalblog.entity.UserEntity;
import com.ensa.medicalblog.graphql.model.Author;

public class AuthorMapper {
    public static Author toModel(UserEntity userEntity) {
        return Author.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .build();
    }
}
