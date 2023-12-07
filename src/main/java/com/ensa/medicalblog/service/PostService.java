package com.ensa.medicalblog.service;

import com.ensa.medicalblog.dto.request.CreatePostRequest;
import com.ensa.medicalblog.dto.response.GetPostResponse;
import com.ensa.medicalblog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(CreatePostRequest createPostRequest);
    GetPostResponse getPostById(String id);
}
