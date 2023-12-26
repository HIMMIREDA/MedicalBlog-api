package com.ensa.medicalblog.graphql.input;

import com.ensa.medicalblog.entity.TagEntity;
import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostInput {
    @GraphQLNonNull
    private String title;

    @GraphQLNonNull
    private String content;

    private Set<String> tags;
}
