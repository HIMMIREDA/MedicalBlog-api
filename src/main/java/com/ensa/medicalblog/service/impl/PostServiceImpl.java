package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.repository.PostRepository;
import com.ensa.medicalblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public Post createPost(PostInput createPostRequest) {
        PostEntity postEntity = PostEntity.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .build();
        postEntity = postRepository.save(postEntity);
        return Post.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .title(postEntity.getTitle())
                .createdAt(postEntity.getCreatedAt())
                .build();
    }


    @Override
    public Post getPostById(String id) {
        // @TODO: change exception handling later
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return Post.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .createdAt(postEntity.getCreatedAt())
                .build();
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll().stream().map(
                postEntity -> Post.builder()
                        .id(postEntity.getId())
                        .title(postEntity.getTitle())
                        .createdAt(postEntity.getCreatedAt())
                        .content(postEntity.getContent())
                        .build()
        ).toList();
    }
}
