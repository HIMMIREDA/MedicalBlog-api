package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.mapper.PostMapper;
import com.ensa.medicalblog.repository.PostRepository;
import com.ensa.medicalblog.service.ImageService;
import com.ensa.medicalblog.service.PostService;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ImageService imageService;


    @Override
    public Post createPost(PostInput createPostRequest, Part file) throws IOException {
        PostEntity postEntity = PostMapper.toEntity(createPostRequest);
        postEntity.setImage(imageService.uploadFile(file));
        postEntity = postRepository.save(postEntity);

        return PostMapper.toModel(postEntity);
    }

    @Override
    public Post getPostById(String id) {
        // @TODO: change exception handling later
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.toModel(postEntity);
    }


    @Override
    public List<Post> getPosts() {
        return postRepository.findAll().stream().map(PostMapper::toModel).toList();
    }
}
