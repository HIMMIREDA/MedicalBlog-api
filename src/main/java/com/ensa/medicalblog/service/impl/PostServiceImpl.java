package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.dto.CreatePostRequest;
import com.ensa.medicalblog.entity.Post;
import com.ensa.medicalblog.repository.PostRepository;
import com.ensa.medicalblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    @Override
    public Post createPost(CreatePostRequest createPostRequest) {
        Post post = Post.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .build();

        return postRepository.save(post);
    }
}
