package com.ensa.medicalblog.service.impl;

import com.ensa.medicalblog.entity.*;
import com.ensa.medicalblog.graphql.input.CommentInput;
import com.ensa.medicalblog.graphql.input.PostInput;
import com.ensa.medicalblog.graphql.model.Post;
import com.ensa.medicalblog.repository.*;
import com.ensa.medicalblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private TagRepository tagRepository;
    private PostTagRepository postTagRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;

    @Override
    public Post createPost(PostInput createPostRequest) {

        PostEntity postEntity = PostEntity.builder()
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .tags(createPostRequest.getTags())
                .build();
        postEntity = postRepository.save(postEntity);

        List<TagEntity> postTags = createPostRequest.getTags().stream()
                .filter(tag -> tagRepository.findByTagName(tag).isEmpty())
                .map(tagName -> TagEntity.builder().tagName(tagName).build())
                .toList();

        tagRepository.saveAll(postTags);

        for (String tagName : createPostRequest.getTags()){
            PostTagEntity postTagEntity = PostTagEntity.builder().tagId(tagRepository.findByTagName(tagName).get().getId()).postId(postEntity.getId()).build();
            postTagRepository.save(postTagEntity);
        }

        return Post.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .title(postEntity.getTitle())
                .createdAt(postEntity.getCreatedAt())
                .tags(postEntity.getTags())
                .likes(postEntity.getLikes())
                .comments(postEntity.getComments())
                .build();
    }


    @Override
    public Post getPostById(String id) {
        // @TODO: change exception handling later
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return Post.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .title(postEntity.getTitle())
                .createdAt(postEntity.getCreatedAt())
                .tags(postEntity.getTags())
                .likes(postEntity.getLikes())
                .comments(postEntity.getComments())
                .build();
    }

    @Override
    public List<Post> getPostsByTag(String tag) {
        return postTagRepository.findByTagId(tag).stream()
                .map(postTag -> Post.builder()
                        .id(postTag.getPostId())
                        .title(getPostById(postTag.getPostId()).getTitle())
                        .content(getPostById(postTag.getPostId()).getContent())
                        .createdAt(getPostById(postTag.getPostId()).getCreatedAt())
                        .tags(getPostById(postTag.getPostId()).getTags())
                        .likes(getPostById(postTag.getPostId()).getLikes())
                        .comments(getPostById(postTag.getPostId()).getComments())
                        .build())
                .toList();

    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll().stream().map(
                postEntity -> Post.builder()
                        .id(postEntity.getId())
                        .content(postEntity.getContent())
                        .title(postEntity.getTitle())
                        .createdAt(postEntity.getCreatedAt())
                        .tags(postEntity.getTags())
                        .likes(postEntity.getLikes())
                        .comments(postEntity.getComments())
                        .build()
        ).toList();
    }

    @Override
    public Post comment(CommentInput commentInput) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));

        PostEntity postEntity = postRepository.findById(commentInput.getPostId()).orElseThrow(() -> new RuntimeException("Post not found"));
        postEntity.getComments().add(commentInput.getContent());
        postRepository.save(postEntity);

        CommentEntity comment = CommentEntity.builder()
                .postId(postEntity.getId())
                .userId(user.getId())
                .content(commentInput.getContent())
                .build();
        commentRepository.save(comment);

        return Post.builder()
                .id(postEntity.getId())
                .content(postEntity.getContent())
                .title(postEntity.getTitle())
                .createdAt(postEntity.getCreatedAt())
                .tags(postEntity.getTags())
                .likes(postEntity.getLikes())
                .comments(postEntity.getComments())
                .build();
    }

    @Override
    public void like(String postId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postEntity.setLikes(postEntity.getLikes() + 1);
        postRepository.save(postEntity);

        LikeEntity likeEntity = LikeEntity.builder()
                .userId(user.getId())
                .postId(postEntity.getId())
                .liked(true)
                .build();
        likeRepository.save(likeEntity);
    }

    @Override
    public void unlike(String postId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));

        LikeEntity likeEntity = likeRepository.findByPostIdAndUserId(postId,user.getId()).orElseThrow(()->new RuntimeException("Post must me liked to unlike"));
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        if(likeEntity.getLiked().equals(true)) {
            postEntity.setLikes(postEntity.getLikes() - 1);
            likeEntity.setLiked(false);
            postRepository.save(postEntity);
            likeRepository.save(likeEntity);
        }

    }
}
