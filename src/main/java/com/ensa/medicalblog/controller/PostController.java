package com.ensa.medicalblog.controller;

import com.ensa.medicalblog.dto.request.CreatePostRequest;
import com.ensa.medicalblog.entity.Post;
import com.ensa.medicalblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PostMapping
    public Map<String, String> createPost(@RequestBody CreatePostRequest createPostRequest){
        Post post = postService.createPost(createPostRequest);
        return Map.ofEntries(Map.entry("id", post.getId()));
    }

}
