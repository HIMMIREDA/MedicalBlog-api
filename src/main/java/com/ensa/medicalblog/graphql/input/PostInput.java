package com.ensa.medicalblog.graphql.input;

import io.leangen.graphql.annotations.GraphQLNonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostInput {
    @GraphQLNonNull
    private String title;

    @GraphQLNonNull
    private String content;
}
