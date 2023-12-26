package com.ensa.medicalblog.resolver.mutation;

import com.ensa.medicalblog.graphql.input.CommentInput;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.service.PostService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLRootContext;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.spqr.spring.autoconfigure.DefaultGlobalContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@GraphQLApi
@Slf4j
public class PostMutation {
    private final PostService postService;

    @GraphQLMutation
    @PreAuthorize("hasRole(T(com.ensa.medicalblog.entity.Role).USER)")
    public @GraphQLNonNull Post createPost(@GraphQLNonNull PostInput postInput, @GraphQLRootContext DefaultGlobalContext<ServletWebRequest> context) throws ServletException, IOException {
        HttpServletRequest request = context.getNativeRequest().getNativeRequest(HttpServletRequest.class);
        return postService.createPost(postInput, request.getPart("file"));
    }


    @GraphQLMutation
    @PreAuthorize("hasRole(T(com.ensa.medicalblog.entity.Role).USER)")
    public @GraphQLNonNull Post comment(@GraphQLNonNull CommentInput commentInput){
        return postService.comment(commentInput);
    }

    @GraphQLMutation
    @PreAuthorize("hasRole(T(com.ensa.medicalblog.entity.Role).USER)")
    public void like(@GraphQLNonNull String postId){
        postService.like(postId);
    }

    @GraphQLMutation
    @PreAuthorize("hasRole(T(com.ensa.medicalblog.entity.Role).USER)")
    public void unlike(@GraphQLNonNull String postId){
        postService.unlike(postId);
    }

}
