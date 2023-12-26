package com.ensa.medicalblog.service;

import com.ensa.medicalblog.entity.PostEntity;
import com.ensa.medicalblog.graphql.input.CommentInput;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostInput postInput);

    Post getPostById(String id);

    List<Post> getPostsByTag(String tag);

    List<Post> getPosts();

    Post comment(CommentInput commentInput);

    public void react(String postId);


}
