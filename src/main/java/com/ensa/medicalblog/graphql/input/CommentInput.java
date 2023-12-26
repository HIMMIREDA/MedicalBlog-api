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
public class CommentInput {
    @GraphQLNonNull
    private String postId;
    @GraphQLNonNull
    private String content;
}
