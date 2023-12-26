package com.ensa.medicalblog.resolver.mutation;

import com.ensa.medicalblog.graphql.input.CommentInput;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.service.PostService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@GraphQLApi
public class PostMutation {
    private final PostService postService;

    @GraphQLMutation
    @PreAuthorize("hasRole(T(com.ensa.medicalblog.entity.Role).USER)")
    public @GraphQLNonNull Post createPost(@GraphQLNonNull PostInput postInput){
        return postService.createPost(postInput);
    }

    @GraphQLMutation
    @PreAuthorize("hasRole(T(com.ensa.medicalblog.entity.Role).USER)")
    public @GraphQLNonNull Post comment(@GraphQLNonNull CommentInput commentInput){
        return postService.comment(commentInput);
    }

}
