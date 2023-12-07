package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.dto.request.CreatePostRequest;
import com.ensa.medicalblog.dto.response.GetPostResponse;
import com.ensa.medicalblog.entity.Post;
import com.ensa.medicalblog.repository.PostRepository;
import com.ensa.medicalblog.service.PostService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@GraphQLApi
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @GraphQLMutation(name = "createPost", description = "create a post")
    @Override
    public Post createPost(@GraphQLArgument(name = "post") CreatePostRequest createPostRequest) {
        Post post = Post.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .build();

        return postRepository.save(post);
    }


    @GraphQLQuery(name = "getPostById", description = "get post by id")
    @Override
    public GetPostResponse getPostById(@GraphQLArgument() String id) {
        // @TODO: change exception handling later
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return GetPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
