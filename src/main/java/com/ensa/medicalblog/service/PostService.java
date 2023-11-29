package com.ensa.medicalblog.service;

import com.ensa.medicalblog.dto.CreatePostRequest;
import com.ensa.medicalblog.entity.Post;

public interface PostService {
    Post createPost(CreatePostRequest createPostRequest);
}
