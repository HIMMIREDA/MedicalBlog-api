package com.ensa.medicalblog.service;

import com.ensa.medicalblog.entity.CommentEntity;
import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.graphql.input.CommentInput;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import jakarta.servlet.http.Part;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post createPost(PostInput postInput, Part file) throws IOException;

    Post getPostById(String id);

    List<Post> getPostsByTag(String tag);

    List<Post> getPosts(int offset, int limit);

    CommentEntity comment(CommentInput commentInput);

    public void react(String postId);


}
